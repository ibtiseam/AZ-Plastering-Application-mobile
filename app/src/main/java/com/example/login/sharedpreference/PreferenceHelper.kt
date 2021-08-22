package com.example.login.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.example.login.data.network.responses.UserResponse
import com.google.gson.Gson





object PreferenceHelper {

    const val KEY_USER = "key_user_connected"

    fun defaultPrefs(context: Context): SharedPreferences
            = PreferenceManager.getDefaultSharedPreferences(context)

    fun customPrefs(context: Context, name: String): SharedPreferences
            = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = this.edit()
        operation(editor)
        editor.apply()
    }

    /**
     * puts a value for the given [key].
     */
    operator fun SharedPreferences.set(key: String, value: Any?)
            = when (value) {
        is String? -> edit { it.putString(key, value) }
        is Int -> edit { it.putInt(key, value) }
        is Boolean -> edit { it.putBoolean(key, value) }
        is Float -> edit { it.putFloat(key, value) }
        is Long -> edit { it.putLong(key, value) }
        else -> throw UnsupportedOperationException("Not yet implemented")
    }

    fun getUserConnected(context: Context) : UserResponse? {
        val gson = Gson()
        return gson.fromJson(defaultPrefs(context = context).getString(KEY_USER,""),UserResponse::class.java)
    }

    fun setUserConnected(context: Context,user:UserResponse) {
       val gson = Gson()
       val json = gson.toJson(user)
       defaultPrefs(context).set(KEY_USER,json)
    }

    fun clearDefaultSharedPreference(context: Context){
        defaultPrefs(context).edit().clear()
    }

    inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T
            = when (T::class) {
        String::class -> getString(key, defaultValue as? String ?: "") as T
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T
        Long::class -> getLong(key, defaultValue as? Long ?: -1) as T
        else -> throw UnsupportedOperationException("Not yet implemented")
    }
}