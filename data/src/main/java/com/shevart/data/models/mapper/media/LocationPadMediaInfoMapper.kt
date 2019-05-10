package com.shevart.data.models.mapper.media

import com.shevart.data.models.ApiLocationPad
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.MediaInfo
import javax.inject.Inject

class LocationPadMediaInfoMapper
@Inject constructor() : Mapper<ApiLocationPad, MediaInfo>() {
    override fun map(from: ApiLocationPad) =
        MediaInfo(
            infoLink = from.infoURL ?: "",
            wikiLink = from.wikiURL ?: "",
            mapLink = from.mapURL ?: ""
        )
}