package com.shevart.data.models

import com.google.gson.annotations.SerializedName

data class ApiRocket(
    @SerializedName(ID)
    val id: Long,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(CONFIGURATION)
    val configuration: String,
    @SerializedName(FAMILY_NAME)
    val familyName: String,
    @SerializedName(AGENCIES)
    val agencies: List<ApiAgency>,
    @SerializedName(WIKI_URL)
    val wikiURL: String,
    @SerializedName(INFO_URLS)
    val infoUrls: List<String>,
    @SerializedName(INFO_URL)
    val infoURL: String,
    @SerializedName(IMAGE_SIZES)
    val imageSizes: List<Int>,
    @SerializedName(IMAGE_URL)
    val imageUrl: String
)
