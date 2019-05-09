package com.shevart.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Launch(
    @SerializedName(ID)
    val id: Long,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(VIDEO_URLS)
    val videoUrls: List<String>,
    @SerializedName(NET_DATE)
    val netDate: Date,
    @SerializedName(ROCKET)
    val rocket: Rocket,
    @SerializedName(MISSIONS)
    val missions: List<Mission>,
    @SerializedName(LAUNCH_SERVICE_PROVIDER)
    val launchServiceProvider: LaunchServiceProvider
)
