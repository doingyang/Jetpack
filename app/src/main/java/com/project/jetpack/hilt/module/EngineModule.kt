package com.project.jetpack.hilt.module

import com.project.jetpack.hilt.annotation.BindElectricEngine
import com.project.jetpack.hilt.annotation.BindGasEngine
import com.project.jetpack.hilt.engine.ElectricEngine
import com.project.jetpack.hilt.engine.GasEngine
import com.project.jetpack.hilt.interfaces.Engine

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class EngineModule {

    @BindGasEngine
    @Binds
    abstract fun bindEngine(gasEngine: GasEngine): Engine

    @BindElectricEngine
    @Binds
    abstract fun bindElectricEngine(electricEngine: ElectricEngine): Engine

}