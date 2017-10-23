package com.morcinek.workout.common.di

import com.morcinek.workout.home.HomeActivity
import com.morcinek.workout.common.di.modules.AndroidModule
import com.morcinek.workout.common.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class, ApplicationModule::class))
interface ApplicationComponent {

    fun inject(activity: HomeActivity)
}