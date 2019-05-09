package com.shevart.domain.models.launch

data class MediaInfo(
    val infoLink: String,
    val wikiLink: String,
    val infoLinks: List<String>,
    val images: List<Image>
)