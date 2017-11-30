package com.morcinek.workout

import android.app.Application
import com.morcinek.workout.common.di.ApplicationComponent
import com.morcinek.workout.common.di.DaggerApplicationComponent
import com.morcinek.workout.common.di.modules.AndroidModule
import com.morcinek.workout.common.di.modules.ApplicationModule
import com.morcinek.workout.common.di.modules.DataModule
import com.morcinek.workout.common.firebase.di.modules.FirebaseModule

class Application : Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerApplicationComponent.builder()
                .androidModule(AndroidModule(this))
                .applicationModule(ApplicationModule())
                .firebaseModule(FirebaseModule())
                .dataModule(DataModule())
                .build()
    }
}