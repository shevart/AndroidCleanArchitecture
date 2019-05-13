package com.shevart.data.remote.impl.rest.api

import com.shevart.data.models.OFFSET
import com.shevart.data.remote.impl.rest.response.GetLaunchesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LaunchApi {
    @GET(API_1_4 + LAUNCH + NEXT + COUNT_PARAM)
    fun getLaunches(
        @Path(COUNT_PARAM_NAME) count: Int,
        @Query(OFFSET) offset: Int
    ): Single<GetLaunchesResponse>

    @GET(API_1_4 + LAUNCH + NAME_PARAM)
    fun getLaunchesByName(
        @Path(NAME) name: String,
        @Query(OFFSET) offset: Int,
        @Query(COUNT_PARAM_NAME) count: Int
    ): Single<GetLaunchesResponse>

    @GET(API_1_4 + LAUNCH + LAUNCH_ID_PARAM)
    fun getLaunchById(
        @Path(LAUNCH_ID_PARAM) launchId: Long
    ): Single<GetLaunchesResponse>
}