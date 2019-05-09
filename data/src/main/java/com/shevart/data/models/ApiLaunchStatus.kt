package com.shevart.data.models

data class ApiLaunchStatus(
    val id: Long,
    val name: String,
    val description: String
) {
    companion object {
        const val LAUNCH_GO_ID = 1L
        const val LAUNCH_TBD_ID = 2L
        const val LAUNCH_SUCCESS_ID = 3L
        const val LAUNCH_FAILURE_ID = 4L
        const val LAUNCH_HOLD_ID = 5L
        const val LAUNCH_IN_FLIGHT_ID = 6L
        const val LAUNCH_PARTIAL_FAILURE_ID = 7L
    }
}