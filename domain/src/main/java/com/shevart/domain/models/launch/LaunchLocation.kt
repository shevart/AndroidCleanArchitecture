package com.shevart.domain.models.launch

data class LaunchLocation(
    val id: Long,
    val name: String,
    val countryCode: String,
    val mediaInfo: MediaInfo,
    val pads: List<LocationPad>
)