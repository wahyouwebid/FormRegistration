package com.ujangwahyu.testamarbank.modules.data.model.dummy

import com.ujangwahyu.testamarbank.modules.domain.model.EnumItem

data class DataEnumModel(
    val name: String
) {
    fun toDomain(): EnumItem {
        return EnumItem(
            name = this.name
        )
    }
}
