package com.ujangwahyu.testamarbank.modules.data.model.province


import com.google.gson.annotations.SerializedName

data class ProvinceResponse(
    @SerializedName("data")
    val data: List<DataProvinceModel>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String
)