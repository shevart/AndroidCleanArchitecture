package com.shevart.data.models.mapper

import com.shevart.data.di.name.DataMapperName
import com.shevart.data.models.ApiLocationPad
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Coordinate
import com.shevart.domain.models.launch.LocationPad
import com.shevart.domain.models.launch.MediaInfo
import javax.inject.Inject
import javax.inject.Named

class LocationPadMapper
@Inject constructor(
    @Named(DataMapperName.DATA_MAPPER_LOCATION_PAD)
    private val mediaInfoMapper: Mapper<ApiLocationPad, MediaInfo>
) : Mapper<ApiLocationPad, LocationPad>() {
    override fun map(from: ApiLocationPad) =
        LocationPad(
            id = from.id,
            name = from.name,
            mediaInfo = mediaInfoMapper.map(from),
            coordinate = Coordinate(
                latitude = from.latitude,
                longitude = from.longitude
            )
        )
}