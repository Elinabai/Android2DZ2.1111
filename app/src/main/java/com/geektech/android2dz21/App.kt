package com.geektech.android2dz21

import android.app.Application
import android.content.SharedPreferences
import android.media.tv.interactive.AppLinkInfo
import com.geektech.android2dz21.utils.PreferenceHelper

class App : Application(){

    companion object{
        val preferenceHelper = PreferenceHelper()
    }

    override fun onCreate() {
        super.onCreate()
        initPref()
    }

    private fun initPref(){
        preferenceHelper.unit(this)
    }
}