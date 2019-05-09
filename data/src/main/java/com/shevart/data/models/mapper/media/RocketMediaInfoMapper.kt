package com.shevart.data.models.mapper.media

import com.shevart.data.models.ApiRocket
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.MediaInfo

class RocketMediaInfoMapper : Mapper<ApiRocket, MediaInfo>() {
    override fun map(from: ApiRocket) = MediaInfo(
        infoLink = from.infoURL,
        wikiLink = from.wikiURL,
        infoLinks = from.infoUrls,
        images = emptyList()
    )
}