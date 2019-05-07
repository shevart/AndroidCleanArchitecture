package com.shevart.domain.models.launch

sealed class StatusType {
    object LaunchingNow : StatusType()
    object Successfully : StatusType()
    object Failed : StatusType()
    object InFlight : StatusType()
    object Canceled : StatusType()
    object Scheduled : StatusType()
    object Hold : StatusType()
    object Unknown : StatusType()
}