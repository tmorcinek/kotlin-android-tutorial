package com.morcinek.workout

import android.app.Application
import com.morcinek.workout.common.di.ApplicationComponent
import com.morcinek.workout.common.di.DaggerApplicationComponent
import com.morcinek.workout.common.di.modules.AndroidModule

class Application : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder().androidModule(AndroidModule(this)).build()
    }
}