package com.shevart.data.remote.impl

import com.shevart.data.remote.RemoteDataProvider
import com.shevart.data.remote.impl.rest.api.LaunchApi
import javax.inject.Inject

class NetworkProvider
@Inject constructor(
    private val launchApi: LaunchApi
) : RemoteDataProvider {

}