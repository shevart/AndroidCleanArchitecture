package com.shevart.data.models.mapper

import com.shevart.data.models.LaunchStatus
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.StatusType
import com.shevart.domain.models.launch.StatusType.*

class LaunchStatusMapper : Mapper<LaunchStatus, StatusType>() {
    override fun map(from: LaunchStatus): StatusType =
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

    override fun reverseMap(to: StatusType): LaunchStatus {
        throw UnsupportedOperationException()
    }

    override fun reverseMapList(typeList: List<StatusType>): List<LaunchStatus> {
        throw UnsupportedOperationException()
    }
}