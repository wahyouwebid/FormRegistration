package com.ujangwahyu.testamarbank.modules.domain.interactor

import com.ujangwahyu.testamarbank.modules.data.model.dummy.Education
import com.ujangwahyu.testamarbank.modules.data.model.dummy.HousingType
import com.ujangwahyu.testamarbank.modules.domain.model.ErrorForm
import com.ujangwahyu.testamarbank.modules.domain.repository.FormRepository
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class FormInteractorTest {

    @Mock
    private lateinit var repository: FormRepository
    private lateinit var formInteractor: FormInteractor

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        formInteractor = FormInteractor(repository, CompositeDisposable())
    }

    @Test
    fun testValidateNationalId_InvalidEmpty_ReturnsValidationResultFalseAndErrorMessage() {
        val nationalId = ""

        val result = formInteractor.validateNationalId(nationalId)

        assertEquals(false, result.successful)
        assertEquals(ErrorForm.NATIONAL_ID_EMPTY.message, result.errorMessage)
    }

    @Test
    fun testValidateNationalId_InvalidLength_ReturnsValidationResultFalseAndErrorMessage() {
        val nationalId = "1234567890"

        val result = formInteractor.validateNationalId(nationalId)

        assertEquals(false, result.successful)
        assertEquals(ErrorForm.NATIONAL_ID_INVALID_LENGTH.message, result.errorMessage)
    }

    @Test
    fun testValidateNationalId_Valid_ReturnsValidationResultTrue() {
        val nationalId = "3202460212932322"

        val result = formInteractor.validateNationalId(nationalId)

        assertEquals(true, result.successful)
        assertEquals("", result.errorMessage)
    }

    @Test
    fun testValidateBankAccountNo_InvalidEmpty_ReturnsValidationResultFalseAndErrorMessage() {
        val bankAccountNo = ""

        val result = formInteractor.validateBankAccountNo(bankAccountNo)

        assertEquals(false, result.successful)
        assertEquals(ErrorForm.BANK_ACCOUNT_NO_EMPTY.message, result.errorMessage)
    }

    @Test
    fun testValidateBankAccountNo_InvalidLength_ReturnsValidationResultFalseAndErrorMessage() {
        val bankAccountNo = "1234567"

        val result = formInteractor.validateBankAccountNo(bankAccountNo)

        assertEquals(false, result.successful)
        assertEquals(ErrorForm.BANK_ACCOUNT_NO_INVALID_LENGTH.message, result.errorMessage)
    }

    @Test
    fun testValidateBankAccountNo_Valid_ReturnsValidationResultTrue() {
        val bankAccountNo = "12345678123123"

        val result = formInteractor.validateBankAccountNo(bankAccountNo)

        assertEquals(true, result.successful)
        assertEquals("", result.errorMessage)
    }

    @Test
    fun testValidateEducation_InvalidEmpty_ReturnsValidationResultFalseAndErrorMessage() {
        val education = ""

        val result = formInteractor.validateEducation(education)

        assertEquals(false, result.successful)
        assertEquals(ErrorForm.EDUCATION_EMPTY.message, result.errorMessage)
    }

    @Test
    fun testValidateEducation_Valid_ReturnsValidationResultTrue() {
        val education = Education.S1.name

        val result = formInteractor.validateEducation(education)

        assertEquals(true, result.successful)
        assertEquals("", result.errorMessage)
    }

    @Test
    fun testValidateDateOfBirth_InvalidEmpty_ReturnsValidationResultFalseAndErrorMessage() {
        val dateOfBirth = ""

        val result = formInteractor.validateDateOfBirth(dateOfBirth)

        assertEquals(false, result.successful)
        assertEquals(ErrorForm.DOB_EMPTY.message, result.errorMessage)
    }

    @Test
    fun testValidateDateOfBirth_InvalidFormat_ReturnsValidationResultFalseAndErrorMessage() {
        val dateOfBirth = "01/08/1996"

        val result = formInteractor.validateDateOfBirth(dateOfBirth)

        assertEquals(false, result.successful)
        assertEquals(ErrorForm.DOB_INVALID_FORMATTER.message, result.errorMessage)
    }

    @Test
    fun testValidateDateOfBirth_Valid_ReturnsValidationResultTrue() {
        val dateOfBirth = "01-08-1996"

        val result = formInteractor.validateDateOfBirth(dateOfBirth)

        assertEquals(true, result.successful)
        assertEquals("", result.errorMessage)
    }

    @Test
    fun testValidateDomicile_InvalidEmpty_ReturnsValidationResultFalseAndErrorMessage() {
        val domicile = ""

        val result = formInteractor.validateDomicile(domicile)

        assertEquals(false, result.successful)
        assertEquals(ErrorForm.DOMICILE_EMPTY.message, result.errorMessage)
    }

    @Test
    fun testValidateDomicile_Valid_ReturnsValidationResultTrue() {
        val domicile = "Perumahan Serua Residence"

        val result = formInteractor.validateDomicile(domicile)

        assertEquals(true, result.successful)
        assertEquals("", result.errorMessage)
    }

    @Test
    fun testValidateHousingType_InvalidEmpty_ReturnsValidationResultFalseAndErrorMessage() {
        val housingType = ""

        val result = formInteractor.validateHousingType(housingType)

        assertEquals(false, result.successful)
        assertEquals(ErrorForm.HOUSING_TYPE_EMPTY.message, result.errorMessage)
    }

    @Test
    fun testValidateHousingType_Valid_ReturnsValidationResultTrue() {
        val housingType = HousingType.Rumah.name

        val result = formInteractor.validateHousingType(housingType)

        assertEquals(true, result.successful)
        assertEquals("", result.errorMessage)
    }

    @Test
    fun testValidateHousingNumber_InvalidEmpty_ReturnsValidationResultFalseAndErrorMessage() {
        val housingNumber = ""

        val result = formInteractor.validateHouseNumber(housingNumber)

        assertEquals(false, result.successful)
        assertEquals(ErrorForm.HOUSING_NO_EMPTY.message, result.errorMessage)
    }

    @Test
    fun testValidateHousingNo_Valid_ReturnsValidationResultTrue() {
        val housingNo = "A1/E20"

        val result = formInteractor.validateHouseNumber(housingNo)

        assertEquals(true, result.successful)
        assertEquals("", result.errorMessage)
    }

    @Test
    fun testValidateProvince_InvalidEmpty_ReturnsValidationResultFalseAndErrorMessage() {
        val province = ""

        val result = formInteractor.validateProvince(province)

        assertEquals(false, result.successful)
        assertEquals(ErrorForm.PROVINCE_NO_EMPTY.message, result.errorMessage)
    }

    @Test
    fun testValidateProvince_Valid_ReturnsValidationResultTrue() {
        val province = "LAMPUNG"

        val result = formInteractor.validateProvince(province)

        assertEquals(true, result.successful)
        assertEquals("", result.errorMessage)
    }
}