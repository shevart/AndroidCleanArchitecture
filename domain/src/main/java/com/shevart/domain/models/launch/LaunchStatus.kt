package com.shevart.domain.models.launch

sealed class LaunchStatus {
    object Successfully: LaunchStatus()
    object Failed: LaunchStatus()
    object Canceled: LaunchStatus()
    object Scheduled: LaunchStatus()
    object Unknown: LaunchStatus()
}