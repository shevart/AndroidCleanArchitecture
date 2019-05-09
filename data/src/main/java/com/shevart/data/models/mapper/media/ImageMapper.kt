package com.shevart.data.models.mapper.media

import com.shevart.data.models.mapper.media.ImageMapper.SourceData
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Image

class ImageMapper : Mapper<SourceData, List<Image>>() {
    override fun map(from: SourceData): List<Image> {
        TODO()
    }

    private fun createImage(link: String, resolution: Int) =
            String.format(link, resolution)

    class SourceData(
        val link: String,
        val resolutions: List<Int>
    )
}