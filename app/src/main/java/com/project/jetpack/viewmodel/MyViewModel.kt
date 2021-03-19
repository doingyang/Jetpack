package com.project.jetpack.viewmodel

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Message
import android.util.Log
import androidx.lifecycle.ViewModel

/**
 * ViewModel类只有一个生命周期方法，那就是onCleared()，
 * 我们通常需要在这个方法中进行一些资源的释放，避免内存泄漏
 * 注意：ViewModel 绝不能引用视图View、Lifecycle 或可能存储对 Activity 上下文的引用的任何类，避免存在内存泄漏。
 */
class MyViewModel : ViewModel() {

    @SuppressLint("HandlerLeak")
    var handler: Handler = object : Handler() {
        var i = 0
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            Log.i("TAG", "handleMessage: " + i++)
            sendEmptyMessageDelayed(0, 5000)
        }
    }

    public fun startHandler() {
        handler.sendEmptyMessageDelayed(0, 5000)
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TAG", "onCleared: ")
        handler.removeMessages(0)
    }
}