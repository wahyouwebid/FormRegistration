package com.ujangwahyu.testamarbank.modules.data.di

import com.ujangwahyu.testamarbank.modules.data.api.ApiService
import com.ujangwahyu.testamarbank.modules.data.repository.FormDataRepository
import com.ujangwahyu.testamarbank.modules.domain.repository.FormRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FormDataModule {

    @Provides
    fun provideRepository(
        apiService: ApiService
    ): FormRepository {
        return FormDataRepository(apiService)
    }

}