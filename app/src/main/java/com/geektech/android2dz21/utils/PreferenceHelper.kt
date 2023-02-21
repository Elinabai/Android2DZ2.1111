package com.geektech.android2dz21.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceHelper {

    private lateinit var sharedPreference: SharedPreferences

    fun unit(context: Context){
        sharedPreference = context.getSharedPreferences("settings",Context.MODE_PRIVATE)
    }

    var saveBoolean: Boolean
    set(value) = sharedPreference.edit().putBoolean("key", value).apply()
    get() = sharedPreference.getBoolean("key",false)

    var signUp:Boolean
    set(value) = sharedPreference.edit().putBoolean("sign",value).apply()
    get() = sharedPreference.getBoolean("sign",false)
}