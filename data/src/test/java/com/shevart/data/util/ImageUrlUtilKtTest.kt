package com.shevart.data.util

import org.junit.Assert.*
import org.junit.Test


class ImageUrlUtilKtTest {
    @Test
    fun `test - prepare valid url`() {
        // perform
        val result = VALID_IMAGE_URL.prepareImageUrlForFormat()

        // check
        assertEquals(VALID_PREPARED_URL, result)
    }
}