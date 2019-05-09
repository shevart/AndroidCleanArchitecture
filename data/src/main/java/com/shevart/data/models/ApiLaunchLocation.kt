package com.shevart.data.models

import com.google.gson.annotations.SerializedName

data class ApiLaunchLocation(
    @SerializedName(ID)
    val id: Long,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(INFO_URL)
    val infoURL: String,
    @SerializedName(WIKI_URL)
    val wikiURL: String,
    @SerializedName(COUNTRY_CODE)
    val countryCode: String,
    @SerializedName(PADS)
    val pads: List<LocationPad>
)