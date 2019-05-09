package com.shevart.domain.models.launch

sealed class LaunchStatus {
    object LaunchingNow : LaunchStatus()
    object Successfully : LaunchStatus()
    object Failed : LaunchStatus()
    object InFlight : LaunchStatus()
    object Canceled : LaunchStatus()
    object Scheduled : LaunchStatus()
    object Hold : LaunchStatus()
    object Unknown : LaunchStatus()
}