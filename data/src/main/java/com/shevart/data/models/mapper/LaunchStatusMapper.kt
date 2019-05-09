package com.shevart.data.models.mapper

import com.shevart.data.models.ApiLaunchStatus
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_FAILURE_ID
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_GO_ID
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_HOLD_ID
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_IN_FLIGHT_ID
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_PARTIAL_FAILURE_ID
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_SUCCESS_ID
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_TBD_ID
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.StatusType
import com.shevart.domain.models.launch.StatusType.*

class LaunchStatusMapper : Mapper<ApiLaunchStatus, StatusType>() {
    override fun map(from: ApiLaunchStatus): StatusType =
        when(from.id) {
            LAUNCH_GO_ID -> LaunchingNow
            LAUNCH_TBD_ID -> Scheduled
            LAUNCH_SUCCESS_ID -> Successfully
            LAUNCH_FAILURE_ID -> Failed
            LAUNCH_HOLD_ID -> Hold
            LAUNCH_IN_FLIGHT_ID -> InFlight
            LAUNCH_PARTIAL_FAILURE_ID -> Canceled
            else -> Unknown
        }

    override fun reverseMap(to: StatusType): ApiLaunchStatus {
        throw UnsupportedOperationException()
    }

    override fun reverseMapList(typeList: List<StatusType>): List<ApiLaunchStatus> {
        throw UnsupportedOperationException()
    }
}