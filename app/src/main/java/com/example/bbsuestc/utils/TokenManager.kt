package com.example.bbsuestc.utils

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

object TokenManager {
    private val sharedPreferences: SharedPreferences by lazy {
        val prefName = "TokenPref"
        val mode = Context.MODE_PRIVATE
        val context = MyApplication.instance // 这里 YourApplication 改成你的 Application 类的名称
        context.getSharedPreferences(prefName, mode)
    }

    private const val KEY_TOKEN = "token"
    private const val KEY_SECRET = "secret"

    fun saveTokenAndSecret(token: String, secret: String) {
        val editor = sharedPreferences.edit()
        editor.putString(KEY_TOKEN, token)
        editor.putString(KEY_SECRET, secret)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, "")
    }

    fun getSecret(): String? {
        return sharedPreferences.getString(KEY_SECRET, "")
    }

    fun clearTokenAndSecret() {
        val editor = sharedPreferences.edit()
        editor.remove(KEY_TOKEN)
        editor.remove(KEY_SECRET)
        editor.apply()
    }
}


