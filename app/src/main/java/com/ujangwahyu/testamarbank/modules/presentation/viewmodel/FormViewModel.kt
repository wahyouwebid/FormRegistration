package com.ujangwahyu.testamarbank.modules.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.ujangwahyu.testamarbank.R
import com.ujangwahyu.testamarbank.databinding.FragmentAlamatKtpBinding
import com.ujangwahyu.testamarbank.databinding.FragmentDataDiriBinding
import com.ujangwahyu.testamarbank.modules.domain.model.EnumItem
import com.ujangwahyu.testamarbank.modules.domain.model.ProvinceItem
import com.ujangwahyu.testamarbank.modules.domain.state.FormResultState
import com.ujangwahyu.testamarbank.modules.domain.usecase.FormUseCase
import com.ujangwahyu.testamarbank.modules.presentation.model.FormDataDiriParams
import com.ujangwahyu.testamarbank.modules.presentation.model.FormDataKtp
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class FormViewModel @Inject constructor(
    val useCase: FormUseCase
): ViewModel() {

    val pagePosition: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    /** province **/
    val provinceState: MutableLiveData<FormResultState> by lazy {
        MutableLiveData()
    }

    val provinceList: MutableLiveData<List<ProvinceItem>> by lazy {
        MutableLiveData()
    }

    val province: MutableLiveData<ProvinceItem> by lazy {
        MutableLiveData()
    }

    /** Education **/
    val educationList: MutableLiveData<List<EnumItem>> by lazy {
        MutableLiveData()
    }

    val education: MutableLiveData<EnumItem> by lazy {
        MutableLiveData()
    }

    /** Housing Type **/
    val housingTypeList: MutableLiveData<List<EnumItem>> by lazy {
        MutableLiveData()
    }

    val housingType: MutableLiveData<EnumItem> by lazy {
        MutableLiveData()
    }

    /** Submit Success **/
    val submitDataDiri: MutableLiveData<FormDataDiriParams> by lazy {
        MutableLiveData()
    }

    val submitDataKtp: MutableLiveData<FormDataKtp> by lazy {
        MutableLiveData()
    }

    val errorMessage: MutableLiveData<Int> by lazy {
        MutableLiveData()
    }

    fun setPosition(pageType: String) {
        pagePosition.postValue(pageType)
    }

    fun getProvince() {
        useCase.getProvince {
            provinceState.postValue(it)
        }
    }

    fun getEducation() {
        useCase.getEducation {
            educationList.postValue(it)
        }
    }

    fun getHousingType() {
        useCase.getHousingType {
            housingTypeList.postValue(it)
        }
    }

    fun submitDataDiri(
        params: FormDataDiriParams,
        binding: FragmentDataDiriBinding,
        navigation: NavController?,
    ) {
        if (validateDataDiri(params, binding)) {
            submitDataDiri.postValue(params)
            errorMessage.postValue(null)
            navigation?.navigate(
                R.id.action_dataDiriFragment_to_alamatKtpFragment,
            )
        }
    }

    /** Validate Data Diri **/
    private fun validateDataDiri(
        params: FormDataDiriParams,
        binding: FragmentDataDiriBinding
    ): Boolean {
        var isValid = true

        if (params.nationalId?.length != 16) {
            binding.etNationalId.showInline(R.string.title_error_national_id_16, isWarn = true)
            isValid = false
        }

        if (params.fullName?.isEmpty() == true) {
            binding.etFullName.showInline(R.string.title_error_full_name, isWarn = true)
            isValid = false
        }

        if (params.bankAccountNo?.isEmpty() == true && params.bankAccountNo.length < 8) {
            binding.etBankAccountNo.showInline(R.string.title_error_account_no, isWarn = true)
            isValid = false
        }

        if (params.education?.isEmpty() == true) {
            binding.etEducation.showInline(R.string.title_error_education, isWarn = true)
            isValid = false
        }

        if (params.dateOfBirth?.isEmpty() == true) {
            binding.etDateOfBirth.showInline(R.string.title_error_date_of_birth, isWarn = true)
            isValid = false
        }

        when {
            params.nationalId?.length != 16 &&
            params.fullName?.isEmpty() == true &&
            params.bankAccountNo?.isEmpty() == true  && params.bankAccountNo.length < 8 &&
            params.education?.isEmpty() == true &&
            params.dateOfBirth?.isEmpty() == true -> errorMessage.postValue(R.string.title_error_all_field)
            params.nationalId?.length != 16 -> errorMessage.postValue(R.string.title_error_national_id_16)
            params.fullName?.isEmpty() == true -> errorMessage.postValue(R.string.title_error_full_name)
            params.bankAccountNo?.isEmpty() == true  && params.bankAccountNo.length < 8 -> errorMessage.postValue(R.string.title_error_account_no)
            params.education?.isEmpty() == true -> errorMessage.postValue(R.string.title_error_education)
            params.dateOfBirth?.isEmpty() == true -> errorMessage.postValue(R.string.title_error_date_of_birth)
        }

        return isValid
    }

    fun submitDataKtp(
        params: FormDataKtp,
        binding: FragmentAlamatKtpBinding,
        navigation: NavController?,
    ) {
        if (validateKtp(params, binding)) {
            submitDataKtp.postValue(params)
            errorMessage.postValue(null)
            navigation?.navigate(
                R.id.action_alamatKtpFragment_to_reviewDataFragment,
            )
        }
    }

    /** Validate Data KTP **/
    private fun validateKtp(
        params: FormDataKtp,
        binding: FragmentAlamatKtpBinding
    ): Boolean {
        var isValid = true

        if (params.domicileAddress?.isEmpty() == true) {
            binding.etDomicileAddress.showInline(R.string.title_error_domicile_address, isWarn = true)
            isValid = false
        }

        if (params.housingType?.isEmpty() == true) {
            binding.etHousingType.showInline(R.string.title_error_housing_type, isWarn = true)
            isValid = false
        }

        if (params.houseNo?.isEmpty() == true ) {
            binding.etNo.showInline(R.string.title_error_housing_number, isWarn = true)
            isValid = false
        }

        if (params.province?.isEmpty() == true) {
            binding.etProvince.showInline(R.string.title_error_province, isWarn = true)
            isValid = false
        }

        when {
            params.domicileAddress?.isEmpty() == true &&
            params.housingType?.isEmpty() == true &&
            params.houseNo?.isEmpty() == true &&
            params.province?.isEmpty() == true -> errorMessage.postValue(R.string.title_error_all_field)

            params.domicileAddress?.length != 16 -> errorMessage.postValue(R.string.title_error_domicile_address)
            params.housingType?.isEmpty() == true -> errorMessage.postValue(R.string.title_error_housing_type)
            params.houseNo?.isEmpty() == true  -> errorMessage.postValue(R.string.title_error_housing_number)
            params.province?.isEmpty() == true -> errorMessage.postValue(R.string.title_error_province)
        }

        return isValid
    }


}