package com.project.jetpack.datastore

import android.app.Application

/**
 * 工厂类，生成PreferencesDataStore的实例
 */
object StoreFactory {

    @JvmStatic
    fun providePreferencesDataStore(context: Application): IDataStore {
        return PreferencesDataStore(context)
    }
}