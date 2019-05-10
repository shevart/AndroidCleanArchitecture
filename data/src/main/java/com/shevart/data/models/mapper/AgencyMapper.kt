package com.shevart.data.models.mapper

import com.shevart.data.di.name.DataMapperName
import com.shevart.data.models.ApiAgency
import com.shevart.data.models.mapper.media.AgencyMediaInfoMapper
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Agency
import com.shevart.domain.models.launch.MediaInfo
import javax.inject.Inject
import javax.inject.Named

class AgencyMapper
@Inject constructor(
    @Named(DataMapperName.DATA_MAPPER_MEDIA_INFO_AGENCY)
    private val agencyMediaInfoMapper: Mapper<ApiAgency, MediaInfo>
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