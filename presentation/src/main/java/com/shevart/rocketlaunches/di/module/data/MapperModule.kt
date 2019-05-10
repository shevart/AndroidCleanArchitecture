@file:Suppress("unused")

package com.shevart.rocketlaunches.di.module.data

import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.LaunchStatus
import com.shevart.rocketlaunches.di.name.MapperNames
import com.shevart.rocketlaunches.models.UILaunchStatus
import com.shevart.rocketlaunches.models.mapper.UILaunchStatusMapper
import dagger.Binds
import dagger.Module
import javax.inject.Named

@Module
abstract class MapperModule {
    @Binds
    @Named(MapperNames.UI_LAUNCH_STATUS_NAME)
    abstract fun bindUILaunchStatusMapper(
        impl: UILaunchStatusMapper
    ): Mapper<LaunchStatus, UILaunchStatus>
}