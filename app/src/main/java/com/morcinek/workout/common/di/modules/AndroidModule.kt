package com.morcinek.workout.common.di.modules

import android.app.Application
import android.content.Context
import android.os.PowerManager
import android.os.Vibrator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule(private val application: Application) {

    @Provides
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providePowerManager() = application.getSystemService(Context.POWER_SERVICE) as PowerManager

    @Provides
    @Singleton
    fun provideVibrator() = application.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
}
