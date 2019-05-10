@file:Suppress("unused")

package com.shevart.rocketlaunches.di.module.data

import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.LaunchStatus
import com.shevart.domain.models.launch.RocketLaunch
import com.shevart.rocketlaunches.di.name.UIMapperNames
import com.shevart.rocketlaunches.models.UILaunch
import com.shevart.rocketlaunches.models.UILaunchStatus
import com.shevart.rocketlaunches.models.mapper.UILaunchMapper
import com.shevart.rocketlaunches.models.mapper.UILaunchStatusMapper
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class UIMapperModule {
    @Binds
    @Named(UIMapperNames.UI_MAPPER_LAUNCH_STATUS)
    abstract fun bindUILaunchStatusMapper(
        impl: UILaunchStatusMapper
    ): Mapper<LaunchStatus, UILaunchStatus>

    @Binds
    @Named(UIMapperNames.UI_MAPPER_LAUNCH)
    abstract fun bindUILaunchMapper(
        impl: UILaunchMapper
    ): Mapper<RocketLaunch, UILaunch>
}