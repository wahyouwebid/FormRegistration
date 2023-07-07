package com.ujangwahyu.testamarbank.modules.domain.model

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = ""
)
