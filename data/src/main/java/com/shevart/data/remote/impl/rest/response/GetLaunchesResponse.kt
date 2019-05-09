package com.shevart.data.remote.impl.rest.response

import com.google.gson.annotations.SerializedName
import com.shevart.data.models.*

data class GetLaunchesResponse(
    @SerializedName(LAUNCHES)
    val launches: List<ApiLaunch>,
    @SerializedName(TOTAL)
    val total: Int,
    @SerializedName(COUNT)
    val count: Int,
    @SerializedName(OFFSET)
    val offset: Int
)