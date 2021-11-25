package com.example.gitbug.App

import android.app.Application
import com.example.gitbug.App.appController
import kotlin.jvm.Synchronized

class appController : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @get:Synchronized
        var instance: appController? = null
            private set
    }
}