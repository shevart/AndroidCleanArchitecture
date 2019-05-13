package com.shevart.data.util

import com.google.gson.GsonBuilder
import java.util.*

object GsonUtil {
    fun getGson() = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .create()!!
}