package com.efedaniel.ulesson

import android.app.Application
import com.efedaniel.ulesson.di.AppComponent
import com.efedaniel.ulesson.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .application(this)
            .build()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}
