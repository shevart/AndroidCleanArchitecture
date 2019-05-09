package com.shevart.data.util

import com.shevart.data.models.*
import java.util.*

fun createApiLaunchesList(count: Int = 3): List<ApiLaunch> =
    createMockObjectsList(count, ::createApiLaunch)

fun createApiAgenciesList(count: Int = 3): List<ApiAgency> =
    createMockObjectsList(count, ::createApiAgency)

fun createApiMissionsList(count: Int = 3): List<ApiMission> =
    createMockObjectsList(count, ::createApiMission)

fun createApiLaunch(id: Long) = ApiLaunch(
    id = id,
    name = "name$id",
    status = 0,
    videoUrls = listOf(
        "videoUrl$id-1",
        "videoUrl$id-2",
        "videoUrl$id-3"
    ),
    netDate = Date(),
    rocket = createApiRocket(id),
    missions = createApiMissionsList(),
    launchServiceProvider = createApiLaunchServiceProvider(id)
)

fun createApiRocket(id: Long) = ApiRocket(
    id = id,
    name = "name$id",
    configuration = "configuration$id",
    familyName = "familyName$id",
    agencies = createApiAgenciesList(3),
    wikiURL = "wikiURL$id",
    infoURL = "infoURL$id",
    infoUrls = listOf(
        "url$id-1",
        "url$id-2",
        "url$id-3"
    ),
    imageSizes = listOf(
        1, 2, 3, 4, 5
    ),
    imageUrl = "imageUrl.$id.jpg"
)

fun createApiAgency(id: Long) = ApiAgency(
    id = id,
    name = "name$id",
    abbrev = "abbrev$id",
    countryCode = "countryCode$id",
    type = 0,
    infoUrl = "infoURL$id",
    wikiUrl = "wikiURL$id",
    infoUrls = listOf(
        "url$id-1",
        "url$id-2",
        "url$id-3"
    )
)

fun createApiMission(id: Long) = ApiMission(
    id = id,
    name = "name$id",
    description = "description$id",
    type = 0,
    wikiUrl = "wikiURL$id",
    typeName = "typeName$id",
    agencies = createApiAgenciesList()
)

fun createApiLaunchServiceProvider(id: Long) = ApiLaunchServiceProvider(
    id = id,
    name = "name$id",
    abbrev = "abbrev$id",
    countryCode = "countryCode$id",
    type = 0,
    infoUrl = "infoURL$id",
    wikiUrl = "wikiURL$id",
    infoUrls = listOf(
        "url$id-1",
        "url$id-2",
        "url$id-3"
    )
)

private inline fun <T> createMockObjectsList(
    count: Int = 10,
    objectCreator: (id: Long) -> T
): List<T> {
    val result = ArrayList<T>()
    for (i in 0 until count) {
        result.add(objectCreator(i.toLong()))
    }
    return result
}

