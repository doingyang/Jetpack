package com.project.jetpack.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

import com.project.jetpack.R
import com.project.jetpack.datastore.DataStoreHelper
import com.project.jetpack.datastore.PreferencesKeys
import com.project.jetpack.datastore.StoreFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope

class DataStoreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_store)
        initEvent()
    }

    private fun initEvent() {
        DataStoreHelper.initDataStore(this)
        lifecycleScope.launch {
            DataStoreHelper.writeData()
            DataStoreHelper.readData()
        }

        lifecycleScope.launch {
            StoreFactory.providePreferencesDataStore(application).putString(PreferencesKeys.KEY_NAME, "YDY")
            val name = StoreFactory.providePreferencesDataStore(application).getString(PreferencesKeys.KEY_NAME)
            Log.i("TAG", "name: $name")
        }
    }
}