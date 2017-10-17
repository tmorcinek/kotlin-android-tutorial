package com.morcinek.workout.common.di

import com.morcinek.workout.home.HomeActivity
import com.morcinek.workout.common.di.modules.AndroidModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class))
interface ApplicationComponent {

    fun inject(activity: HomeActivity)
}