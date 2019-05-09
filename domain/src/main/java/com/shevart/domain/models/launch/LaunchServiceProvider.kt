package com.shevart.domain.models.launch

data class LaunchServiceProvider(
    val id: Long,
    val name: String,
    val abbreviation: String,
    val countryCode: String,
    val type: Int,
    val mediaInfo: MediaInfo
)