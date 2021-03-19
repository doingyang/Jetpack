package com.project.jetpack.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CoroutinesViewModel : ViewModel() {

    val nameLiveData = MutableLiveData<String>()

    fun checkName() {
        // Main-safe，意味着可以直接从UI线程启动；Dispatchers.Main可以省略。
        viewModelScope.launch(Dispatchers.Main) {
            // 这块指定耗时操作使用IO线程
            val name = withContext(Dispatchers.IO) {
                searchFromNet()
            }
            nameLiveData.value = name
        }
    }

    // 耗时操作(如网络请求)
    private suspend fun searchFromNet(): String {
        Log.i("TAG", "searchFromNet: ${Thread.currentThread().name}")
        delay(3_000)
        return "荒"
    }
}