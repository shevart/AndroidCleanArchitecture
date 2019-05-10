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
}