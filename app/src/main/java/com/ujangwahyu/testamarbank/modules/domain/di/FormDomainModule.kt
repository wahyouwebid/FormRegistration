package com.ujangwahyu.testamarbank.modules.domain.di

import com.ujangwahyu.testamarbank.modules.data.repository.FormDataRepository
import com.ujangwahyu.testamarbank.modules.domain.interactor.FormInteractor
import com.ujangwahyu.testamarbank.modules.domain.repository.FormRepository
import com.ujangwahyu.testamarbank.modules.domain.usecase.FormUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.disposables.CompositeDisposable


@Module
@InstallIn(SingletonComponent::class)
class FormDomainModule {

    @Provides
    fun provideInteractor(
        repository: FormRepository,
        disposable: CompositeDisposable
    ): FormUseCase {
        return FormInteractor(repository, disposable)
    }

}