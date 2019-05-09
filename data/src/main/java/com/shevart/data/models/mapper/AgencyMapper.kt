package com.shevart.data.models.mapper

import com.shevart.data.models.ApiAgency
import com.shevart.data.models.mapper.media.AgencyMediaInfoMapper
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Agency
import com.shevart.domain.models.launch.MediaInfo

class AgencyMapper(
    private val agencyMediaInfoMapper: Mapper<ApiAgency, MediaInfo> = AgencyMediaInfoMapper()
) : Mapper<ApiAgency, Agency>() {
    override fun map(from: ApiAgency) =
        Agency(
            id = from.id,
            name = from.name,
            abbreviation = from.abbrev,
            countryCode = from.countryCode,
            mediaInfo = agencyMediaInfoMapper.map(from)
        )
}