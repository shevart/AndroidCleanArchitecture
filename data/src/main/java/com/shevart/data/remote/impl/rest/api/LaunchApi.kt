package com.shevart.data.remote.impl.rest.api

import com.shevart.data.remote.impl.rest.response.GetLaunchStatusesResponse
import io.reactivex.Single
import retrofit2.http.GET

interface LaunchApi {
    @GET(API_1_4 + LAUNCH_STATUS)
    fun getLaunchStatuses(): Single<GetLaunchStatusesResponse>
}