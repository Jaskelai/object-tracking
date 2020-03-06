package com.github.jaskelai.object_tracking.data.local.shared_prefs

interface SharedPrefsProvider {

    fun writeString(key: String, value: String)

    fun readString(key: String): String?

    fun writeBoolean(key: String, value: Boolean)

    fun readBoolean(key: String): Boolean

    fun clearMessage(key: String)
}