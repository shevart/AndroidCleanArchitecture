@file:Suppress("unused")

package com.shevart.data.di

import com.shevart.data.di.name.DataMapperName
import com.shevart.data.models.*
import com.shevart.data.models.mapper.*
import com.shevart.data.models.mapper.media.*
import com.shevart.data.models.mapper.media.ImageMapper.SourceData
import com.shevart.domain.contract.mapper.Mapper
import com.shevart.domain.models.launch.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
abstract class DataMapperModule {
    @Binds
    @Named(DataMapperName.DATA_MAPPER_MEDIA_INFO_AGENCY)
    abstract fun bindAgencyMediaInfoMapper(
        impl: AgencyMediaInfoMapper
    ): Mapper<ApiAgency, MediaInfo>

    @Binds
    @Named(DataMapperName.DATA_MAPPER_IMAGE)
    abstract fun bindImageMapper(
        impl: ImageMapper
    ): Mapper<SourceData, ImagesList>

    @Binds
    @Named(DataMapperName.DATA_MAPPER_MEDIA_INFO_LAUNCH_SERVICE_PROVIDER)
    abstract fun bindLaunchServiceProviderMediaInfoMapper(
        impl: LaunchServiceProviderMediaInfoMapper
    ): Mapper<ApiLaunchServiceProvider, MediaInfo>

    @Binds
    @Named(DataMapperName.DATA_MAPPER_MEDIA_INFO_LAUNCH_PAD)
    abstract fun bindLocationPadMediaInfoMapper(
        impl: LocationPadMediaInfoMapper
    ): Mapper<ApiLocationPad, MediaInfo>

    @Binds
    @Named(DataMapperName.DATA_MAPPER_MEDIA_INFO_MISSION)
    abstract fun bindMissionMediaInfoMapper(
        impl: MissionMediaInfoMapper
    ): Mapper<ApiMission, MediaInfo>

    @Binds
    @Named(DataMapperName.DATA_MAPPER_MEDIA_INFO_ROCKET)
    abstract fun bindRocketMediaInfoMapper(
        impl: RocketMediaInfoMapper
    ): Mapper<ApiRocket, MediaInfo>

    @Binds
    @Named(DataMapperName.DATA_MAPPER_AGENCY)
    abstract fun bindAgencyMapper(
        impl: AgencyMapper
    ): Mapper<ApiAgency, Agency>

    @Binds
    @Named(DataMapperName.DATA_MAPPER_ROCKET)
    abstract fun bindRocketMapper(
        impl: RocketMapper
    ): Mapper<ApiRocket, Rocket>

    @Binds
    @Named(DataMapperName.DATA_MAPPER_MISSION)
    abstract fun bindMissionMapper(
        impl: MissionMapper
    ): Mapper<ApiMission, Mission>

    @Binds
    @Named(DataMapperName.DATA_MAPPER_LOCATION_PAD)
    abstract fun bindLocationPadMapper(
        impl: LocationPadMapper
    ): Mapper<ApiLocationPad, LocationPad>

    @Binds
    @Named(DataMapperName.DATA_MAPPER_LAUNCH_STATUS)
    abstract fun bindLaunchStatusMapper(
        impl: LaunchStatusMapper
    ): Mapper<Int, LaunchStatus>

    @Binds
    @Named(DataMapperName.DATA_MAPPER_LAUNCH_SERVICE_PROVIDER)
    abstract fun bindLaunchServiceProviderMapper(
        impl: LaunchServiceProviderMapper
    ): Mapper<ApiLaunchServiceProvider, LaunchServiceProvider>

    @Binds
    @Named(DataMapperName.DATA_MAPPER_ROCKET_LAUNCH)
    abstract fun bindLaunchMapper(
        impl: LaunchMapper
    ): Mapper<ApiLaunch, RocketLaunch>

    @Binds
    @Named(DataMapperName.DATA_MAPPER_COUNTRY)
    abstract fun bindCountryMapper(
        impl: CountryMapper
    ): Mapper<String, Country>
}