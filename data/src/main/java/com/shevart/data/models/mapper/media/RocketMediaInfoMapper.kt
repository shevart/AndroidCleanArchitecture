package com.shevart.data.models.mapper.media

import com.shevart.data.di.name.DataMapperName
import com.shevart.data.models.ApiRocket
import com.shevart.data.models.ImagesList
import com.shevart.data.models.mapper.media.ImageMapper.SourceData
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Image
import com.shevart.domain.models.launch.MediaInfo
import javax.inject.Inject
import javax.inject.Named

class RocketMediaInfoMapper
@Inject constructor(
    @Named(DataMapperName.DATA_MAPPER_IMAGE)
    private val imageMapper: Mapper<SourceData, ImagesList>
) : Mapper<ApiRocket, MediaInfo>() {
    override fun map(from: ApiRocket) =
        MediaInfo(
            infoLink = from.infoURL ?: "",
            wikiLink = from.wikiURL ?: "",
            infoLinks = from.infoUrls,
            images = from.obtainImages(imageMapper)
        )

    private fun ApiRocket.obtainImages(
        imageMapper: Mapper<SourceData, ImagesList>
    ): List<Image> {
        val param = SourceData(
            link = this.imageUrl,
            resolutions = this.imageSizes
        )
        return imageMapper.map(param).images
    }
}