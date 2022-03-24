package com.example.androidstudystronger

import android.app.Application
import com.example.androidstudystronger.extensions.notNullSingleInit
import kotlin.properties.Delegates


class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: MainApplication by Delegates.notNullSingleInit()
    }

}
