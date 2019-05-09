package com.shevart.data.models

import com.google.gson.annotations.SerializedName

data class LocationPad(
    @SerializedName(ID)
    val id: Long,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(INFO_URL)
    val infoURL: String,
    @SerializedName(WIKI_URL)
    val wikiURL: String,
    @SerializedName(MAP_URL)
    val mapURL: String,
    @SerializedName(LATITUDE)
    val latitude: Double,
    @SerializedName(LONGITUDE)
    val longitude: Double,
    @SerializedName(AGENCIES)
    val agencies: List<ApiAgency>
)