package com.shevart.data.models

import com.google.gson.annotations.SerializedName

data class ApiMission(
    @SerializedName(ID)
    val id: Long,
    @SerializedName(NAME)
    val name: String,
    @SerializedName(DESCRIPTION)
    val description: String,
    @SerializedName(TYPE)
    val type: Int,
    @SerializedName(WIKI_URL)
    val wikiUrl: String,
    @SerializedName(TYPE_NAME)
    val typeName: String,
    @SerializedName(AGENCIES)
    val agencies: List<ApiAgency>
)