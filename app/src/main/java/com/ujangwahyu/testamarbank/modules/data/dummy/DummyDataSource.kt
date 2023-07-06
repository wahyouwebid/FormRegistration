package com.ujangwahyu.testamarbank.modules.data.dummy

import com.ujangwahyu.testamarbank.modules.data.model.dummy.DataEnumModel
import com.ujangwahyu.testamarbank.modules.data.model.dummy.Education
import com.ujangwahyu.testamarbank.modules.data.model.dummy.HousingType

object DummyDataSource {

    fun getEducation(): List<DataEnumModel> {
        return listOf(
            DataEnumModel(Education.SD.name),
            DataEnumModel(Education.SMP.name),
            DataEnumModel(Education.SMA.name),
            DataEnumModel(Education.S1.name),
            DataEnumModel(Education.S2.name),
            DataEnumModel(Education.S3.name),
        )
    }

    fun getHousingType(): List<DataEnumModel> {
        return listOf(
            DataEnumModel(HousingType.Rumah.name),
            DataEnumModel(HousingType.Kantor.name),
        )
    }
}