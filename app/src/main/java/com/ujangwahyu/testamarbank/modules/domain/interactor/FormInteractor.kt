package com.ujangwahyu.testamarbank.modules.domain.interactor

import com.ujangwahyu.testamarbank.modules.domain.repository.FormRepository
import com.ujangwahyu.testamarbank.modules.domain.usecase.FormUseCase

class FormInteractor(
    val repository: FormRepository
): FormUseCase {
}