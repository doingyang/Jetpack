package com.project.jetpack.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * 注意：请确保用于更新界面的 LiveData 对象存储在 ViewModel 对象中，
 * 而不是将其存储在 Activity 或 Fragment 中，避免 Activity 和 Fragment 过于庞大，并且解耦。
 * Activity 和 Fragment只负责显示数据，不负责存储数据状态。
 * 将 LiveData 实例与特定的 Activity 或 Fragment 实例分离开，并使 LiveData 对象在配置更改后继续存在。
 */
class LiveDataViewModel : ViewModel() {

    private var mData :MutableLiveData<String>? = null

    fun getData() :MutableLiveData<String> {
        if (mData == null) {
            mData = MutableLiveData()
        }
        return mData!!
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("TAG", "onCleared: ")
    }
}