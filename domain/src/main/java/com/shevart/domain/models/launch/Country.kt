package com.shevart.domain.models.launch

import com.shevart.domain.models.launch.CountryCode.CHINE_CODE
import com.shevart.domain.models.launch.CountryCode.INDIAN_CODE
import com.shevart.domain.models.launch.CountryCode.RUSSIA_CODE
import com.shevart.domain.models.launch.CountryCode.UNKNOWN_CODE
import com.shevart.domain.models.launch.CountryCode.USA_CODE

object CountryCode {
    const val USA_CODE = "USA"
    const val INDIAN_CODE = "IND"
    const val RUSSIA_CODE = "RUS"
    const val CHINE_CODE = "CHN"
    const val UNKNOWN_CODE = "--"
}

sealed class Country(val countryCode: String) {
    object USA : Country(USA_CODE)
    object Indian : Country(INDIAN_CODE)
    object Russia : Country(RUSSIA_CODE)
    object China : Country(CHINE_CODE)
    data class Unknown(val unknownCountryCode: String) : Country(UNKNOWN_CODE)
}