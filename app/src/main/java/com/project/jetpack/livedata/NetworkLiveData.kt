package com.project.jetpack.livedata

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.LiveData

class NetworkLiveData(val context: Context) : LiveData<String>() {

    companion object {
        const val NETWORK_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"
    }

    private val intentFilter = IntentFilter().apply {
        addAction(NETWORK_ACTION)
    }

    private val mReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == NETWORK_ACTION) {
                value = "网络状态改变"
            }
        }
    }

    /**
     * onActive()：当有一个处于活跃状态的观察者监听LiveData时会被调用
     */
    override fun onActive() {
        context.registerReceiver(mReceiver, intentFilter)
        super.onActive()
    }

    /**
     * onInactive()：当没有任何处于活跃状态的观察者监听LiveData时会被调用
     */
    override fun onInactive() {
        context.unregisterReceiver(mReceiver)
        super.onInactive()
    }

}