package com.shevart.domain.models.launch

data class Rocket(
    val id: Long,
    val name: String,
    val agency: Agency? = null,
    val mediaInfo: MediaInfo
)