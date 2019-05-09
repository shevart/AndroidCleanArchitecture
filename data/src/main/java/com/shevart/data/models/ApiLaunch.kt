package com.shevart.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class ApiLaunch(
    @SerializedName(ID)
    val id: Long,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(VIDEO_URLS)
    val videoUrls: List<String>,
    @SerializedName(NET_DATE)
    val netDate: Date,
    @SerializedName(ROCKET)
    val rocket: ApiRocket,
    @SerializedName(MISSIONS)
    val missions: List<ApiMission>,
    @SerializedName(LAUNCH_SERVICE_PROVIDER)
    val launchServiceProvider: ApiLaunchServiceProvider
)
