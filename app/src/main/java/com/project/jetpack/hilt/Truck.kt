package com.project.jetpack.hilt

import com.project.jetpack.hilt.annotation.BindElectricEngine
import com.project.jetpack.hilt.annotation.BindGasEngine
import com.project.jetpack.hilt.interfaces.Engine
import javax.inject.Inject

class Truck @Inject constructor(var driver: Driver) {

    @BindGasEngine
    @Inject
    lateinit var gasEngine: Engine

    @BindElectricEngine
    @Inject
    lateinit var electricEngine: Engine

    fun deliver() {
        gasEngine.start()
        electricEngine.start()
        println("Truck is delivering cargo. Driven by $driver")
        gasEngine.shutdown()
        electricEngine.shutdown()
    }

}