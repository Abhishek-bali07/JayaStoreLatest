package com.jaya.app.store.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class JayaStore: Application() {


    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object{
        lateinit var  appInstance : JayaStore

    }

}