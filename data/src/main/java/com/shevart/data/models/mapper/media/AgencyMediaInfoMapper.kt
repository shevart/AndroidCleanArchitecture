package com.shevart.data.models.mapper.media

import com.shevart.data.models.ApiAgency
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.MediaInfo

class AgencyMediaInfoMapper : Mapper<ApiAgency, MediaInfo>() {
    override fun map(from: ApiAgency) =
        MediaInfo(
            infoLink = from.infoUrl,
            wikiLink = from.wikiUrl,
            infoLinks = from.infoUrls,
            images = emptyList()
        )
}