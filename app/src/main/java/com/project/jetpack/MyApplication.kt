package com.project.jetpack

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.project.jetpack.lifecycler.ApplicationLifecycleObserver
import dagger.hilt.android.HiltAndroidApp

/**
 * 自定义Application中可以不写任何代码，但是必须要加上一个 @HiltAndroidApp 注解，这是使用 Hilt 的一个必备前提
 */
@HiltAndroidApp
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // ProcessLifecycleOwner（监听整个应用的生命周期）
        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())
    }
}