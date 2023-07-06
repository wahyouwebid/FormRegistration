package com.ujangwahyu.testamarbank.modules.data.model.province


import com.google.gson.annotations.SerializedName
import com.ujangwahyu.testamarbank.modules.domain.model.ProvinceItem

data class DataProvinceModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
) {
    fun toDomain(): ProvinceItem {
        return ProvinceItem(
            id = this.name,
            name = this.name
        )
    }
}