package ru.ltcnt.basher.app

import android.app.Application

class BasherApplication: Application() {
    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .apiModule(ApiModule())
            .build()
    }

}