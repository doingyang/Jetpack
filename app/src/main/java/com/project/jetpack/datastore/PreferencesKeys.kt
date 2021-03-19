package com.project.jetpack.datastore

import androidx.datastore.preferences.core.preferencesKey

/**
 * 存储DataStore的Key的类
 */
object PreferencesKeys {
    val KEY_COUNT = preferencesKey<Int>("key_count")
    val KEY_FLAG = preferencesKey<Boolean>("key_flag")
    val KEY_PRICE = preferencesKey<Float>("key_price")
    val KEY_NAME = preferencesKey<String>("key_name")
    val KEY_TIME = preferencesKey<Long>("key_time")
    val KEY_MONEY = preferencesKey<Double>("key_money")
}