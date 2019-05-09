package com.shevart.domain.util

import com.shevart.domain.contract.scheduler.SchedulerProvider
import com.shevart.domain.models.launch.*
import com.shevart.domain.models.launch.LaunchStatus.Successfully
import io.reactivex.schedulers.Schedulers
import java.util.*

val schedulerProvider = object : SchedulerProvider {
    override fun io() = Schedulers.trampoline()

    override fun observe() = Schedulers.trampoline()
}

fun createRocketLaunchesList(count: Int = 1) =
    createMockObjectsList(count, ::createRocketLaunch)

fun createMissionsList(count: Int = 1) =
    createMockObjectsList(count, ::createMission)

fun createAgenciesList(count: Int = 1) =
    createMockObjectsList(count, ::createAgency)

fun createRocketLaunch(id: Long) =
    RocketLaunch(
        id = id,
        name = "name$id",
        date = Date(),
        rocket = createRocket(id),
        status = Successfully,
        missions = createMissionsList(),
        favorite = false
    )

fun createRocket(id: Long) =
    Rocket(
        id = id,
        name = "name$id",
        agency = createAgency(id),
        mediaInfo = createMediaInfo(id)
    )

fun createMission(id: Long) =
    Mission(
        id = id,
        name = "name$id",
        description = "description$id",
        type = 0,
        typeName = "typeName$id",
        agencies = createAgenciesList(),
        mediaInfo = createMediaInfo(id)
    )

fun createAgency(id: Long) =
    Agency(
        id = id,
        name = "name$id",
        abbreviation = "abbreviation$id",
        countryCode = "countryCode$id",
        mediaInfo = createMediaInfo(id)
    )

fun createMediaInfo(id: Long) =
    MediaInfo(
        infoLink = "infoLink$id",
        wikiLink = "wikiLink$id",
        mapLink = "mapLink$id",
        infoLinks = listOf(
            "infoLink$id-1",
            "infoLink$id-2",
            "infoLink$id-3"
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