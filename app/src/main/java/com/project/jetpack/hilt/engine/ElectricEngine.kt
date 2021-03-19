package com.project.jetpack.hilt.engine

import com.project.jetpack.hilt.interfaces.Engine
import javax.inject.Inject

class ElectricEngine @Inject constructor() : Engine {

    override fun start() {
        println("Electric engine start.")
    }

    override fun shutdown() {
        println("Electric engine shutdown.")
    }
}