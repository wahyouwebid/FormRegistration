package com.ujangwahyu.testamarbank.modules.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.ujangwahyu.testamarbank.R
import com.ujangwahyu.testamarbank.databinding.FragmentAlamatKtpBinding
import com.ujangwahyu.testamarbank.databinding.FragmentDataDiriBinding
import com.ujangwahyu.testamarbank.modules.domain.model.EnumItem
import com.ujangwahyu.testamarbank.modules.domain.model.ProvinceItem
import com.ujangwahyu.testamarbank.modules.domain.model.ValidationResult
import com.ujangwahyu.testamarbank.modules.domain.state.FormResultState
import com.ujangwahyu.testamarbank.modules.domain.usecase.FormUseCase
import com.ujangwahyu.testamarbank.modules.presentation.model.FormDataDiriParams
import com.ujangwahyu.testamarbank.modules.presentation.model.FormDataKtp
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


@HiltViewModel
class FormViewModel @Inject constructor(
    private val useCase: FormUseCase
): ViewModel() {

    /** Data Diri **/
    val pagePosition: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    val educationList: MutableLiveData<List<EnumItem>> by lazy {
        MutableLiveData()
    }

    val education: MutableLiveData<EnumItem> by lazy {
        MutableLiveData()
    }

    val submitDataDiri: MutableLiveData<FormDataDiriParams> by lazy {
        MutableLiveData()
    }

    /** Ktp **/
    val provinceState: MutableLiveData<FormResultState> by lazy {
        MutableLiveData()
    }

    val provinceList: MutableLiveData<List<ProvinceItem>> by lazy {
        MutableLiveData()
    }

    val province: MutableLiveData<ProvinceItem> by lazy {
        MutableLiveData()
    }

    val housingTypeList: MutableLiveData<List<EnumItem>> by lazy {
        MutableLiveData()
    }

    val housingType: MutableLiveData<EnumItem> by lazy {
        MutableLiveData()
    }

    val submitDataKtp: MutableLiveData<FormDataKtp> by lazy {
        MutableLiveData()
    }

    val errorMessage: MutableLiveData<String> by lazy {
        MutableLiveData()
    }

    fun setPosition(pageType: String) {
        pagePosition.postValue(pageType)
    }

    fun getEducation() {
        useCase.getEducation {
            educationList.postValue(it)
        }
    }

    fun getProvince() {
        useCase.getProvince {
            provinceState.postValue(it)
        }
    }

    fun getHousingType() {
        useCase.getHousingType {
            housingTypeList.postValue(it)
        }
    }

    fun submitDataDiri(
        navigation: NavController?,
        binding: FragmentDataDiriBinding,
        params: FormDataDiriParams
    ) {
        val nationalId = validateNationalId(binding, params.nationalId.toString())
        val accountNo = validateBankAccountNo(binding, params.bankAccountNo.toString())
        val education = validateEducation(binding, params.education.toString())
        val dateOfBirth = validateDateOfBirth(binding, params.dateOfBirth.toString())

        val hasError = listOf(
            nationalId,
            accountNo,
            education,
            dateOfBirth
        )

        if (!hasError.any { !it.successful }) {
            submitDataDiri.postValue(params)
            errorMessage.postValue(null)
            navigation?.navigate(R.id.action_dataDiriFragment_to_alamatKtpFragment)
        } else {
            hasError.reversed().filter { !it.successful }.map {
                errorMessage.postValue(it.errorMessage)
            }
        }
    }

    fun submitDataKtp(
        navigation: NavController?,
        binding: FragmentAlamatKtpBinding,
        params: FormDataKtp
    ) {
        val domicile = validateDomicile(binding, params.domicileAddress.toString())
        val housingType = validateHousingType(binding, params.housingType.toString())
        val houseNumber = validateHouseNumber(binding, params.houseNo.toString())
        val province = validateProvince(binding, params.province.toString())

        val hasError = listOf(
            domicile,
            housingType,
            houseNumber,
            province
        )

        if (!hasError.any { !it.successful }) {
            submitDataKtp.postValue(params)
            errorMessage.postValue(null)
            navigation?.navigate(R.id.action_alamatKtpFragment_to_reviewDataFragment)
        } else {
            hasError.reversed().filter { !it.successful }.map {
                errorMessage.postValue(it.errorMessage)
            }
        }
    }

    fun validateNationalId(
        binding: FragmentDataDiriBinding,
        nationalId: String
    ): ValidationResult {
        val result = useCase.validateNationalId(nationalId)
        binding.etNationalId.isError(result.errorMessage)
        return result
    }

    fun validateBankAccountNo(
        binding: FragmentDataDiriBinding,
        accountNo: String
    ): ValidationResult {
        val result = useCase.validateBankAccountNo(accountNo)
        binding.etBankAccountNo.isError(result.errorMessage)
        return result
    }

    fun validateEducation(
        binding: FragmentDataDiriBinding,
        education: String
    ): ValidationResult {
        val result = useCase.validateEducation(education)
        binding.etEducation.isError(result.errorMessage)
        return result
    }

    fun validateDateOfBirth(
        binding: FragmentDataDiriBinding,
        dob: String
    ): ValidationResult {
        val result = useCase.validateDateOfBirth(dob)
        binding.etDateOfBirth.isError(result.errorMessage)
        return result
    }

    fun validateDomicile(
        binding: FragmentAlamatKtpBinding,
        domicile: String
    ): ValidationResult {
        val result = useCase.validateDomicile(domicile)
        binding.etDomicileAddress.isError(result.errorMessage)
        return result
    }

    fun validateHousingType(
        binding: FragmentAlamatKtpBinding,
        housingType: String
    ): ValidationResult {
        val result = useCase.validateHousingType(housingType)
        binding.etHousingType.isError(result.errorMessage)
        return result
    }

    fun validateHouseNumber(
        binding: FragmentAlamatKtpBinding,
        houseNumber: String
    ): ValidationResult {
        val result = useCase.validateHouseNumber(houseNumber)
        binding.etNo.isError(result.errorMessage)
        return result
    }

    fun validateProvince(
        binding: FragmentAlamatKtpBinding,
        province: String
    ): ValidationResult {
        val result = useCase.validateProvince(province)
        binding.etProvince.isError(result.errorMessage)
        return result
    }
}