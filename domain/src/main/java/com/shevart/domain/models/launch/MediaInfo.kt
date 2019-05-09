package com.shevart.domain.models.launch

data class MediaInfo(
    val infoLink: String = "",
    val wikiLink: String = "",
    val mapLink: String = "",
    val infoLinks: List<String> = emptyList(),
    val images: List<Image> = emptyList(),
    val videoLinks: List<String> = emptyList()
)