package com.shevart.domain.models.launch

data class Agency(
    val id: Long,
    val name: String,
    val abbreviation: String,
    val countryCode: String,
    val mediaInfo: MediaInfo
)