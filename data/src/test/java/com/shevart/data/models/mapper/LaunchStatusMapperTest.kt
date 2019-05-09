package com.shevart.data.models.mapper

import com.shevart.data.models.ApiLaunchStatus
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_FAILURE_ID
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_GO_ID
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_HOLD_ID
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_IN_FLIGHT_ID
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_PARTIAL_FAILURE_ID
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_SUCCESS_ID
import com.shevart.data.models.ApiLaunchStatus.Companion.LAUNCH_TBD_ID
import com.shevart.domain.models.launch.StatusType
import com.shevart.domain.models.launch.StatusType.*
import org.junit.Assert.assertEquals
import org.junit.Test

class LaunchStatusMapperTest {
    @Test(expected = NotImplementedError::class)
    fun `test - unsupported reverse single map`() {
        // prepare
        val mapper = createMapper()
        // perform
        mapper.reverseMap(LaunchingNow)
    }

    @Test
    fun `test - launchingNow`() {
        testMapper(
            sourceValue = createLaunchStatus(LAUNCH_GO_ID),
            expectedResult = LaunchingNow
        )
    }

    @Test
    fun `test - Scheduled`() {
        testMapper(
            sourceValue = createLaunchStatus(LAUNCH_TBD_ID),
            expectedResult = Scheduled
        )
    }

    @Test
    fun `test - Successfully`() {
        testMapper(
            sourceValue = createLaunchStatus(LAUNCH_SUCCESS_ID),
            expectedResult = Successfully
        )
    }

    @Test
    fun `test - Failed`() {
        testMapper(
            sourceValue = createLaunchStatus(LAUNCH_FAILURE_ID),
            expectedResult = Failed
        )
    }

    @Test
    fun `test - Hold`() {
        testMapper(
            sourceValue = createLaunchStatus(LAUNCH_HOLD_ID),
            expectedResult = Hold
        )
    }

    @Test
    fun `test - InFlight`() {
        testMapper(
            sourceValue = createLaunchStatus(LAUNCH_IN_FLIGHT_ID),
            expectedResult = InFlight
        )
    }

    @Test
    fun `test - Canceled`() {
        testMapper(
            sourceValue = createLaunchStatus(LAUNCH_PARTIAL_FAILURE_ID),
            expectedResult = Canceled
        )
    }

    private fun testMapper(sourceValue: ApiLaunchStatus, expectedResult: StatusType) {
        // prepare
        val mapper = createMapper()

        // perform
        val result = mapper.map(sourceValue.id)

        // check
        assertEquals(expectedResult, result)
    }

    private fun createMapper() = LaunchStatusMapper()

    private fun createLaunchStatus(id: Int) = ApiLaunchStatus(
        id = id,
        name = "name_$id",
        description = "description_$id"
    )
}