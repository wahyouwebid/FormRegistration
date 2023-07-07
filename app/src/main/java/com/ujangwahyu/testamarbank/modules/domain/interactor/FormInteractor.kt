package com.ujangwahyu.testamarbank.modules.domain.interactor

import com.ujangwahyu.testamarbank.modules.domain.model.EnumItem
import com.ujangwahyu.testamarbank.modules.domain.model.ErrorForm
import com.ujangwahyu.testamarbank.modules.domain.model.ValidationResult
import com.ujangwahyu.testamarbank.modules.domain.repository.FormRepository
import com.ujangwahyu.testamarbank.modules.domain.state.FormResultState
import com.ujangwahyu.testamarbank.modules.domain.usecase.FormUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
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
        return Single.just(nationalId)
            .flatMap { string ->
                when {
                    string.isEmpty() -> {
                        Single.just(ValidationResult(false, ErrorForm.NATIONAL_ID_EMPTY.message))
                    }

                    string.length != 16 -> {
                        Single.just(ValidationResult(false, ErrorForm.NATIONAL_ID_MUST_16.message))
                    }

                    else -> {
                        Single.just(ValidationResult(true))
                    }
                }
            }
            .blockingGet()
    }

    override fun validateBankAccountNo(accountNo: String): ValidationResult {
        return Single.just(accountNo)
            .flatMap { string ->
                when {
                    string.isEmpty() -> {
                        Single.just(ValidationResult(false, ErrorForm.BANK_ACCOUNT_NO_EMPTY.message))
                    }

                    string.length < 8 -> {
                        Single.just(ValidationResult(false, ErrorForm.BANK_ACCOUNT_NO_8.message))
                    }

                    else -> {
                        Single.just(ValidationResult(true))
                    }
                }
            }
            .blockingGet()
    }

    override fun validateEducation(education: String): ValidationResult {
        return Single.just(education)
            .flatMap { string ->
                when {
                    string.isEmpty() -> {
                        Single.just(ValidationResult(false, ErrorForm.EDUCATION_EMPTY.message))
                    }

                    else -> {
                        Single.just(ValidationResult(true))
                    }
                }
            }
            .blockingGet()
    }

    override fun validateDateOfBirth(dob: String): ValidationResult {
        return Single.just(dob)
            .flatMap { string ->
                when {
                    string.isEmpty() -> {
                        Single.just(ValidationResult(false, ErrorForm.DOB_EMPTY.message))
                    }

                    else -> {
                        Single.just(ValidationResult(true))
                    }
                }
            }
            .blockingGet()
    }

    override fun validateDomicile(domicile: String): ValidationResult {
        return Single.just(domicile)
            .flatMap { string ->
                when {
                    string.isEmpty() -> {
                        Single.just(ValidationResult(false, ErrorForm.HOUSING_TYPE_EMPTY.message))
                    }

                    else -> {
                        Single.just(ValidationResult(true))
                    }
                }
            }
            .blockingGet()
    }

    override fun validateHousingType(housingType: String): ValidationResult {
        return Single.just(housingType)
            .flatMap { string ->
                when {
                    string.isEmpty() -> {
                        Single.just(ValidationResult(false, ErrorForm.HOUSING_TYPE_EMPTY.message))
                    }

                    else -> {
                        Single.just(ValidationResult(true))
                    }
                }
            }
            .blockingGet()
    }

    override fun validateHouseNumber(houseNumber: String): ValidationResult {
        return Single.just(houseNumber)
            .flatMap { string ->
                when {
                    string.isEmpty() -> {
                        Single.just(ValidationResult(false, ErrorForm.HOUSING_NO_EMPTY.message))
                    }

                    else -> {
                        Single.just(ValidationResult(true))
                    }
                }
            }
            .blockingGet()
    }

    override fun validateProvince(province: String): ValidationResult {
        return Single.just(province)
            .flatMap { string ->
                when {
                    string.isEmpty() -> {
                        Single.just(ValidationResult(false, ErrorForm.PROVINCE_NO_EMPTY.message))
                    }

                    else -> {
                        Single.just(ValidationResult(true))
                    }
                }
            }
            .blockingGet()
    }

    override fun clearDisposable() {
        disposable.clear()
    }


}