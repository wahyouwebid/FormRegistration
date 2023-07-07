package com.ujangwahyu.testamarbank.modules.domain.model

enum class ErrorForm(val message: String){
    NATIONAL_ID_EMPTY("National id cannot be empty"),
    NATIONAL_ID_MUST_16("National ID must 16 digits"),
    FULL_NAME_EMPTY("Full name cannot be empty"),
    BANK_ACCOUNT_NO_EMPTY("Account number cannot be empty"),
    BANK_ACCOUNT_NO_8("Bank account number minimum 8 digits"),
    EDUCATION_EMPTY("Education cannot be empty"),
    DOB_EMPTY("Date of birth cannot be empty"),
    DOMICILE_EMPTY("Domicile address cannot be empty"),
    HOUSING_TYPE_EMPTY("Domicile address cannot be empty"),
    HOUSING_NO_EMPTY("Housing number cannot be empty"),
    PROVINCE_NO_EMPTY("Province cannot be empty"),
}