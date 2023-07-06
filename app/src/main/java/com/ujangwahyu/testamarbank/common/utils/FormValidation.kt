package com.ujangwahyu.testamarbank.common.utils

import androidx.core.widget.doOnTextChanged
import com.ujangwahyu.testamarbank.R
import com.ujangwahyu.testamarbank.common.uikit.UIKitEditText

object FormValidation {

    fun UIKitEditText.validateNationalId() {
        this.getEditText().doOnTextChanged { text, _, _, _ ->
            when {
                text?.isEmpty() == true -> {
                    this.showInline(R.string.title_error_national_id, isWarn = false)
                }
                text?.length != 16 -> {
                    this.showInline(R.string.title_error_national_id_16, isWarn = true)
                }

                else -> {
                    this.showInline(R.string.title_required, isWarn = false)
                }
            }
        }
    }

    fun UIKitEditText.validateFullName() {
        this.getEditText().doOnTextChanged { text, _, _, _ ->
            when {
                text?.isEmpty() == true -> {
                    this.showInline(R.string.title_error_full_name, isWarn = true)
                }

                else -> {
                    this.showInline(R.string.title_required, isWarn = false)
                }
            }
        }
    }

    fun UIKitEditText.validateBankAccountNo() {
        this.getEditText().doOnTextChanged { text, _, _, _ ->
            when {
                text?.isEmpty() == true -> {
                    this.showInline(R.string.title_error_account_no, isWarn = true)
                }

                text!!.length < 8 -> {
                    this.showInline(R.string.title_error_account_no_minimum, isWarn = true)
                }

                else -> {
                    this.showInline(R.string.title_required, isWarn = false)
                }
            }
        }
    }

    fun UIKitEditText.validateEducation() {
        this.getEditText().doOnTextChanged { text, _, _, _ ->
            when {
                text?.isEmpty() == true -> {
                    this.showInline(R.string.title_error_education, isWarn = true)
                }
                else -> {
                    this.showInline(R.string.title_required, isWarn = false)
                }
            }
        }
    }

    fun UIKitEditText.validateDateOfBirth() {
        this.getEditText().doOnTextChanged { text, _, _, _ ->
            when {
                text?.isEmpty() == true -> {
                    this.showInline(R.string.title_error_date_of_birth, isWarn = true)
                }
                else -> {
                    this.showInline(R.string.title_required, isWarn = false)
                }
            }
        }
    }


    fun UIKitEditText.validateDomicileAddress() {
        this.getEditText().doOnTextChanged { text, _, _, _ ->
            when {
                text?.isEmpty() == true -> {
                    this.showInline(R.string.title_error_domicile_address, isWarn = true)
                }

                else -> {
                    this.showInline(R.string.title_required, isWarn = false)
                }
            }
        }
    }

    fun UIKitEditText.validateHousingType() {
        this.getEditText().doOnTextChanged { text, _, _, _ ->
            when {
                text?.isEmpty() == true -> {
                    this.showInline(R.string.title_error_housing_type, isWarn = true)
                }
                else -> {
                    this.showInline(R.string.title_required, isWarn = false)
                }
            }
        }
    }

    fun UIKitEditText.validateHouseNumber() {
        this.getEditText().doOnTextChanged { text, _, _, _ ->
            when {
                text?.isEmpty() == true -> {
                    this.showInline(R.string.title_error_housing_number, isWarn = true)
                }
                else -> {
                    this.showInline(R.string.title_required, isWarn = false)
                }
            }
        }
    }


    fun UIKitEditText.validateProvince() {
        this.getEditText().doOnTextChanged { text, _, _, _ ->
            when {
                text?.isEmpty() == true -> {
                    this.showInline(R.string.title_error_province, isWarn = true)
                }
                else -> {
                    this.showInline(R.string.title_required, isWarn = false)
                }
            }
        }
    }

}