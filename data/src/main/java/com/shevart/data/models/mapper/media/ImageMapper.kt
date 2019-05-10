package com.shevart.data.models.mapper.media

import com.shevart.data.models.ImagesList
import com.shevart.data.models.mapper.media.ImageMapper.SourceData
import com.shevart.data.util.prepareImageUrlForFormat
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Image
import javax.inject.Inject

class ImageMapper
@Inject constructor() : Mapper<SourceData, ImagesList>() {
    override fun map(from: SourceData): ImagesList {
        val preparedLink = from.link.prepareImageUrlForFormat()
        val images = from.resolutions
            .map { resolution ->
                Image(
                    resolution = resolution,
                    imageUrl = String.format(preparedLink, resolution)
                )
            }
        return ImagesList(images)
    }

    class SourceData(
        val link: String,
        val resolutions: List<Int>
    )
}