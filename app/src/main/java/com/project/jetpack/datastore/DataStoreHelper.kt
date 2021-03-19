package com.project.jetpack.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map

class DataStoreHelper {

    companion object {

        private var dataStore: DataStore<Preferences>? = null
        private var EXAMPLE_COUNTER = preferencesKey<Int>("example_counter")

        fun initDataStore(context: Context) {
            // 创建一个DataStore实例对象，一般只需要指定参数name就可以，name表示的就是Preferences DataStore的名称，settings为对应的文件名
            dataStore = context.createDataStore(name = "settings")
            // 创建Preferences类实例
            EXAMPLE_COUNTER = preferencesKey<Int>("example_counter")
            // 注意Preferences方式并不是类型安全的，定义的preferencesKey虽然指定了范型是Int，但实际存储在DataStore中的数据可能是别的类型。
            // 使用dataStore的data属性，返回一个Flow对象，最后调用Flow的collect()方法，即可读取数据。
            // 如果要向DataStore中写入数据，可以通过DataStore对象的edit()方法进行编辑，该方法接收一段代码，可以在其中根据需要进行更新。
        }

        suspend fun readData() {
            // 从DataStore中读取数据
            val exampleCounterFlow: Flow<Int> = dataStore!!.data
                .map {
                    it[EXAMPLE_COUNTER] ?: 0
                }
            exampleCounterFlow.collect {
                Log.i("TAG", "readData: read result: $it")
            }
        }

        suspend fun writeData() {
            dataStore!!.edit {
                val currentCounterValue = it[EXAMPLE_COUNTER] ?: 0
                Log.i("TAG", "writeData: " + (currentCounterValue + 1))
                it[EXAMPLE_COUNTER] = currentCounterValue + 1
            }
        }

    }

}