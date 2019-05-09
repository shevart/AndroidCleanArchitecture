package com.shevart.data.models.mapper.media

import com.shevart.data.models.mapper.media.ImageMapper.SourceData
import com.shevart.data.util.prepareImageUrlForFormat
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Image

class ImageMapper : Mapper<SourceData, List<Image>>() {
    override fun map(from: SourceData): List<Image> {
        val preparedLink = from.link.prepareImageUrlForFormat()
        return from.resolutions
            .map { resolution ->
                Image(
                    resolution = resolution,
                    imageUrl = String.format(preparedLink, resolution)
                )
            }
    }

    class SourceData(
        val link: String,
        val resolutions: List<Int>
    )
}