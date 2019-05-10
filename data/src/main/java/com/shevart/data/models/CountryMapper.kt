package com.shevart.data.models

import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.Country
import com.shevart.domain.models.launch.Country.*
import com.shevart.domain.models.launch.CountryCode.CHINE_CODE
import com.shevart.domain.models.launch.CountryCode.INDIAN_CODE
import com.shevart.domain.models.launch.CountryCode.RUSSIA_CODE
import com.shevart.domain.models.launch.CountryCode.USA_CODE
import javax.inject.Inject

class CountryMapper
@Inject constructor() : Mapper<String, Country>() {
    override fun map(from: String): Country =
        when (from) {
            USA_CODE -> USA
            INDIAN_CODE -> Indian
            RUSSIA_CODE -> Russia
            CHINE_CODE -> China
            else -> Unknown(from)
        }
}