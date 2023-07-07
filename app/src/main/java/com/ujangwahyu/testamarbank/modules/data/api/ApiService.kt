package com.ujangwahyu.testamarbank.modules.data.api

import com.ujangwahyu.testamarbank.modules.data.model.province.ProvinceResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("regional/provinsi")
    fun getProvince(
        @Query("api_key") apiKey: String?,
    ): Observable<ProvinceResponse>

}