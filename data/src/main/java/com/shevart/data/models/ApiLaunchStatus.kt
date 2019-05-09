package com.shevart.data.models

data class ApiLaunchStatus(
    val id: Int,
    val name: String,
    val description: String
) {
    companion object {
        const val LAUNCH_GO_ID = 1
        const val LAUNCH_TBD_ID = 2
        const val LAUNCH_SUCCESS_ID = 3
        const val LAUNCH_FAILURE_ID = 4
        const val LAUNCH_HOLD_ID = 5
        const val LAUNCH_IN_FLIGHT_ID = 6
        const val LAUNCH_PARTIAL_FAILURE_ID = 7
    }
}