package com.shevart.domain.models.launch

data class Mission(
    val id: Long,
    val name: String,
    val description: String,
    val type: Int,
    val typeName: String,
    val agencies: List<Agency>,
    val mediaInfo: MediaInfo
)