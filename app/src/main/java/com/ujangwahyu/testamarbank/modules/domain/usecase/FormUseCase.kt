package com.ujangwahyu.testamarbank.modules.domain.usecase

import com.ujangwahyu.testamarbank.modules.domain.model.EnumItem
import com.ujangwahyu.testamarbank.modules.domain.model.ValidationResult
import com.ujangwahyu.testamarbank.modules.domain.state.FormResultState

interface FormUseCase {

    fun getProvince(callback: (data: FormResultState) -> Unit)

    fun getEducation(callback: (data: List<EnumItem>) -> Unit)

    fun getHousingType(callback: (data: List<EnumItem>) -> Unit)

    fun validateNationalId(nationalId: String): ValidationResult

    fun validateBankAccountNo(accountNo: String): ValidationResult

    fun validateEducation(education: String): ValidationResult

    fun validateDateOfBirth(dob: String): ValidationResult

    fun validateDomicile(domicile: String): ValidationResult

    fun validateHousingType(housingType: String): ValidationResult

    fun validateHouseNumber(houseNumber: String): ValidationResult

    fun validateProvince(province: String): ValidationResult

    fun clearDisposable()

}