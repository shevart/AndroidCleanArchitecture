package com.shevart.domain.models.launch

import java.util.*

data class RocketLaunch(
    val id: Long,
    val name: String,
    val date: Date,
    val rocket: Rocket,
    val status: LaunchStatus,
    val country: Country,
    val missions: List<Mission>,
    val favorite: Boolean
)