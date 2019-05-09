package com.shevart.data.models

import com.google.gson.annotations.SerializedName

data class ApiAgency(
    @SerializedName(ID)
    val id: Long,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(ABBREV)
    val abbrev: String,
    @SerializedName(COUNTRY_CODE)
    val countryCode: String,
    @SerializedName(TYPE)
    val type: Int,
    @SerializedName(INFO_URL)
    val infoUrl: String?,
    @SerializedName(WIKI_URL)
    val wikiUrl: String?,
    @SerializedName(INFO_URLS)
    val infoUrls: List<String>
)