package com.project.jetpack.datastore

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/**
 * Preferences方式实现类
 */
class PreferencesDataStore(val context: Application) : IDataStore {

    // 指定名字
    private val PREFERENCES_NAME = "prefs_datastore"

    // 创建dataStore
    var dataStore: DataStore<Preferences> = context.createDataStore(
        name = PREFERENCES_NAME
    )

    override suspend fun putBoolean(key: Preferences.Key<Boolean>, value: Boolean) {
        dataStore.edit { it[key] = value }
    }

    override suspend fun getBoolean(key: Preferences.Key<Boolean>): Boolean {
        return dataStore.data.map { it[key] ?: false }.first()
    }

    override suspend fun putInt(key: Preferences.Key<Int>, value: Int) {
        dataStore.edit { it[key] = value }
    }

    override suspend fun getInt(key: Preferences.Key<Int>): Int {
        return dataStore.data.map { it[key] ?: 0 }.first()
    }

    override suspend fun putLong(key: Preferences.Key<Long>, value: Long) {
        dataStore.edit { it[key] = value }
    }

    override suspend fun getLong(key: Preferences.Key<Long>): Long {
        return dataStore.data.map { it[key] ?: 0L }.first()
    }

    override suspend fun putDouble(key: Preferences.Key<Double>, value: Double) {
        dataStore.edit { it[key] = value }
    }

    override suspend fun getDouble(key: Preferences.Key<Double>): Double {
        return dataStore.data.map { it[key] ?: 0.0 }.first()
    }

    override suspend fun putFloat(key: Preferences.Key<Float>, value: Float) {
        dataStore.edit { it[key] = value }
    }

    override suspend fun getFloat(key: Preferences.Key<Float>): Float {
        return dataStore.data.map { it[key] ?: 0F }.first()
    }

    override suspend fun putString(key: Preferences.Key<String>, value: String) {
        dataStore.edit { it[key] = value }
    }

    override suspend fun getString(key: Preferences.Key<String>): String {
        return dataStore.data.map { it[key] ?: "" }.first()
    }

    override fun spToDataStore() {
        /**
         *  传入 migrations 参数，构建一个 DataStore 之后
         *  需要执行 一次读取 或者 写入，DataStore 才会自动合并 SharedPreference 文件内容
         */
        dataStore = context.createDataStore(
            name = PREFERENCES_NAME,
            migrations = listOf(
                SharedPreferencesMigration(
                    context,
                    // TODO: 2021/3/17 0017 ???
                    "SpUtils.SHARE_PREFERENCES_NAME"
                )
            )
        )
    }
}