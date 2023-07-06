package com.ujangwahyu.testamarbank.modules.domain.repository

import com.ujangwahyu.testamarbank.modules.domain.model.EnumItem
import com.ujangwahyu.testamarbank.modules.domain.model.ProvinceItem
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface FormRepository {

    fun getProvince(): Observable<List<ProvinceItem>>

    fun getHousingType(): Single<List<EnumItem>>

    fun getEducation(): Single<List<EnumItem>>

}