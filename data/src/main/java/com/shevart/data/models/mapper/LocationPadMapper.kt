package com.shevart.data.models.mapper

import com.shevart.data.models.ApiLocationPad
import com.shevart.data.models.mapper.media.LocationPadMediaInfoMapper
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Coordinate
import com.shevart.domain.models.launch.LocationPad
import com.shevart.domain.models.launch.MediaInfo

class LocationPadMapper(
    private val mediaInfoMapper: Mapper<ApiLocationPad, MediaInfo> = LocationPadMediaInfoMapper()
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