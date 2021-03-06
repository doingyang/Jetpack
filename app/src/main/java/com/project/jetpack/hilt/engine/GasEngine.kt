package com.project.jetpack.hilt.engine

import com.project.jetpack.hilt.interfaces.Engine
import javax.inject.Inject

class GasEngine @Inject constructor() : Engine {

    override fun start() {
        println("Gas engine start.")
    }

    override fun shutdown() {
        println("Gas engine shutdown.")
    }
}