package com.ujangwahyu.testamarbank.modules.domain.state

import com.ujangwahyu.testamarbank.modules.domain.model.ProvinceItem

sealed class FormResultState{

    data class Success(val data: List<ProvinceItem>) : FormResultState()

    data class Error(val error: Throwable) : FormResultState()

    object Loading : FormResultState()

}