package com.project.jetpack.lifecycler

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class ApplicationLifecycleObserver : LifecycleObserver {

    /**
     * 应用程序的整个生命周期中只会执行一次
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        println("onCreate")
    }

    /**
     * 应用出现在前台调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        println("onStart")
    }

    /**
     * 应用出现在前台调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() {
        println("onResume")
    }

    /**
     * 应用退到后台调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        println("onPause")
    }

    /**
     * 应用退到后台调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        println("onStop")
    }

    /**
     * 永远不会被调用
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        println("onDestroy")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onAppForeground(){
        Log.i("TAG","====AppLifecycleObserver=====onAppForeground==")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onAppBackgound(){
        Log.i("TAG","====AppLifecycleObserver=====onAppBackgound==")
    }

}