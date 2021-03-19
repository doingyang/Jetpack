package com.project.jetpack.lifecycler

import androidx.lifecycle.LifecycleService

class MyService : LifecycleService() {

    private var mServiceObserver : MyServiceObserver

    init {
        mServiceObserver = MyServiceObserver()
        lifecycle.addObserver(mServiceObserver)
    }
}