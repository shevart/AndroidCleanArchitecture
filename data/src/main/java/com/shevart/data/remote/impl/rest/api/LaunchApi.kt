package com.shevart.data.remote.impl.rest.api

import com.shevart.data.remote.impl.rest.response.GetLaunchesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface LaunchApi {
//  launch/next/7?offset=0
    @GET(API_1_4 + LAUNCH + NEXT + COUNT_PARAM ) //+ PARAM
    fun getLaunches(@Path(COUNT_PARAM_NAME) count: Int): Single<GetLaunchesResponse>
}