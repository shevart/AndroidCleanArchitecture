package com.shevart.data.remote.impl.rest.response

import com.google.gson.annotations.SerializedName
import com.shevart.data.models.*

data class GetLaunchStatusesResponse(
    @SerializedName(TYPES)
    val statuses: List<LaunchStatus>,
    @SerializedName(TOTAL)
    val total: Int,
    @SerializedName(COUNT)
    val count: Int,
    @SerializedName(OFFSET)
    val offset: Int
)