package com.shevart.data.models.mapper.media

import com.shevart.data.models.ApiRocket
import com.shevart.data.models.mapper.media.ImageMapper.SourceData
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Image
import com.shevart.domain.models.launch.MediaInfo

class RocketMediaInfoMapper(
    private val imageMapper: ImageMapper = ImageMapper()
) : Mapper<ApiRocket, MediaInfo>() {
    override fun map(from: ApiRocket) =
        MediaInfo(
            infoLink = from.infoURL ?: "",
            wikiLink = from.wikiURL ?: "",
            infoLinks = from.infoUrls,
            images = obtainImages(from, imageMapper)
        )

    private fun obtainImages(apiRocket: ApiRocket, imageMapper: ImageMapper): List<Image> =
        imageMapper.map(
            SourceData(
                link = apiRocket.imageUrl,
                resolutions = apiRocket.imageSizes
            )
        )
}