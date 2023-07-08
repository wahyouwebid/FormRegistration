package com.ujangwahyu.testamarbank.modules.domain.interactor

import com.ujangwahyu.testamarbank.common.utils.isValidFormat
import com.ujangwahyu.testamarbank.modules.domain.model.EnumItem
import com.ujangwahyu.testamarbank.modules.domain.model.ErrorForm
import com.ujangwahyu.testamarbank.modules.domain.model.ValidationResult
import com.ujangwahyu.testamarbank.modules.domain.repository.FormRepository
import com.ujangwahyu.testamarbank.modules.domain.state.FormResultState
import com.ujangwahyu.testamarbank.modules.domain.usecase.FormUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FormInteractor(
    private val repository: FormRepository,
    private val disposable: CompositeDisposable,
): FormUseCase {

    override fun getProvince(callback: (data: FormResultState) -> Unit) {
        repository.getProvince()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map<FormResultState> { FormResultState.Success(it) }
            .onErrorReturn { FormResultState.Error(it) }
            .startWithItem(FormResultState.Loading)
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun getEducation(callback: (data: List<EnumItem>) -> Unit) {
        repository.getEducation()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun getHousingType(callback: (data: List<EnumItem>) -> Unit) {
        repository.getHousingType()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback)
            .let { disposable.add(it) }
    }

    override fun validateNationalId(nationalId: String): ValidationResult {
        return when {
            nationalId.isEmpty() -> {
                ValidationResult(false, ErrorForm.NATIONAL_ID_EMPTY.message)
            }

            nationalId.length != 16 -> {
                ValidationResult(false, ErrorForm.NATIONAL_ID_INVALID_LENGTH.message)
            }

            else -> {
                ValidationResult(true)
            }
        }
    }

    override fun validateBankAccountNo(accountNo: String): ValidationResult {
        return when {
            accountNo.isEmpty() -> {
                ValidationResult(false, ErrorForm.BANK_ACCOUNT_NO_EMPTY.message)
            }

            accountNo.length < 8 -> {
                ValidationResult(false, ErrorForm.BANK_ACCOUNT_NO_INVALID_LENGTH.message)
            }

            else -> {
                ValidationResult(true)
            }
        }
    }

    override fun validateEducation(education: String): ValidationResult {
        return when {
            education.isEmpty() -> {
                ValidationResult(false, ErrorForm.EDUCATION_EMPTY.message)
            }

            else -> {
                ValidationResult(true)
            }
        }
    }

    override fun validateDateOfBirth(dob: String): ValidationResult {
        return when {
            dob.isEmpty() -> {
                ValidationResult(false, ErrorForm.DOB_EMPTY.message)
            }

            !dob.isValidFormat() -> {
                ValidationResult(false, ErrorForm.DOB_INVALID_FORMATTER.message)
            }

            else -> {
                ValidationResult(true)
            }
        }
    }

    override fun validateDomicile(domicile: String): ValidationResult {
        return when {
            domicile.isEmpty() -> {
                ValidationResult(false, ErrorForm.DOMICILE_EMPTY.message)
            }

            else -> {
                ValidationResult(true)
            }
        }
    }

    override fun validateHousingType(housingType: String): ValidationResult {
        return when {
            housingType.isEmpty() -> {
                ValidationResult(false, ErrorForm.HOUSING_TYPE_EMPTY.message)
            }

            else -> {
                ValidationResult(true)
            }
        }
    }

    override fun validateHouseNumber(houseNumber: String): ValidationResult {
        return when {
            houseNumber.isEmpty() -> {
                ValidationResult(false, ErrorForm.HOUSING_NO_EMPTY.message)
            }

            else -> {
                ValidationResult(true)
            }
        }
    }

    override fun validateProvince(province: String): ValidationResult {
        return when {
            province.isEmpty() -> {
                ValidationResult(false, ErrorForm.PROVINCE_NO_EMPTY.message)
            }

            else -> {
                ValidationResult(true)
            }
        }
    }

    override fun clearDisposable() {
        disposable.clear()
    }

}