package com.shevart.data.datasource.sections

import com.shevart.data.local.LocalDataProvider
import com.shevart.data.remote.RemoteDataProvider

abstract class AbsSection(
    protected val remote: RemoteDataProvider,
    protected val local: LocalDataProvider
)