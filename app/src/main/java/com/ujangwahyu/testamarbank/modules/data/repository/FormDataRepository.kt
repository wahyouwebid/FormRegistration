package com.ujangwahyu.testamarbank.modules.data.repository

import com.ujangwahyu.testamarbank.BuildConfig
import com.ujangwahyu.testamarbank.modules.data.api.ApiService
import com.ujangwahyu.testamarbank.modules.data.model.dummy.DataEnumModel
import com.ujangwahyu.testamarbank.modules.data.model.dummy.Education
import com.ujangwahyu.testamarbank.modules.data.model.dummy.HousingType
import com.ujangwahyu.testamarbank.modules.domain.model.EnumItem
import com.ujangwahyu.testamarbank.modules.domain.model.ProvinceItem
import com.ujangwahyu.testamarbank.modules.domain.repository.FormRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

class FormDataRepository(
    private val apiService: ApiService
): FormRepository {

    override fun getProvince(): Observable<List<ProvinceItem>> {
        return apiService.getProvince(BuildConfig.apiKey).map { response ->
            response.data.map { it.toDomain() }
        }
    }

    override fun getHousingType(): Single<List<EnumItem>> {
        return Single.just(
            listOf(
                DataEnumModel(HousingType.Rumah.name),
                DataEnumModel(HousingType.Kantor.name),
            ).map { it.toDomain() }
        )
    }

    override fun getEducation(): Single<List<EnumItem>> {
        return Single.just(
            listOf(
                DataEnumModel(Education.SD.name),
                DataEnumModel(Education.SMP.name),
                DataEnumModel(Education.SMA.name),
                DataEnumModel(Education.S1.name),
                DataEnumModel(Education.S2.name),
                DataEnumModel(Education.S3.name),
            ).map { it.toDomain() }
        )
    }
}