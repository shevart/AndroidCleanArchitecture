package com.shevart.domain.models.launch

data class LocationPad(
    val id: Long,
    val name: String,
    val mediaInfo: MediaInfo,
    val coordinate: Coordinate
)