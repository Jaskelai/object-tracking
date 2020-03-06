package com.github.jaskelai.object_tracking.data.local.shared_prefs

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPrefsProviderImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : SharedPrefsProvider {

    override fun writeString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun readString(key: String): String? = sharedPreferences.getString(key, null)

    override fun writeBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    override fun readBoolean(key: String): Boolean = sharedPreferences.getBoolean(key, false)

    override fun clearMessage(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}
