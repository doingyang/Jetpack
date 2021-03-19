package com.project.jetpack.datastore

import androidx.datastore.preferences.core.Preferences

interface IDataStore {

    suspend fun putBoolean(key: Preferences.Key<Boolean>, value: Boolean)
    suspend fun getBoolean(key: Preferences.Key<Boolean>): Boolean

    suspend fun putInt(key: Preferences.Key<Int>, value: Int)
    suspend fun getInt(key: Preferences.Key<Int>): Int

    suspend fun putLong(key: Preferences.Key<Long>, value: Long)
    suspend fun getLong(key: Preferences.Key<Long>): Long

    suspend fun putFloat(key: Preferences.Key<Float>, value: Float)
    suspend fun getFloat(key: Preferences.Key<Float>): Float

    suspend fun putDouble(key: Preferences.Key<Double>, value: Double)
    suspend fun getDouble(key: Preferences.Key<Double>): Double

    suspend fun putString(key: Preferences.Key<String>, value: String)
    suspend fun getString(key: Preferences.Key<String>): String

    fun spToDataStore()
}