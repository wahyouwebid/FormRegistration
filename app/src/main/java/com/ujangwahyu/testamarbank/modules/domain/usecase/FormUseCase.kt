package com.ujangwahyu.testamarbank.modules.domain.usecase

import com.ujangwahyu.testamarbank.modules.domain.model.EnumItem
import com.ujangwahyu.testamarbank.modules.domain.state.FormResultState

interface FormUseCase {

    fun getProvince(callback: (data: FormResultState) -> Unit)

    fun getEducation(callback: (data: List<EnumItem>) -> Unit)

    fun getHousingType(callback: (data: List<EnumItem>) -> Unit)

    fun clearDisposable()

}