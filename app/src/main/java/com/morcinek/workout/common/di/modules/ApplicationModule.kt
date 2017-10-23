package com.morcinek.workout.common.di.modules

import android.content.Context
import android.os.PowerManager
import android.os.Vibrator
import com.morcinek.workout.common.NotificationCenter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule() {

    @Provides
    @Singleton
    fun provideNotificationCenter(context: Context, vibrator: Vibrator, powerManager: PowerManager)
            = NotificationCenter(context, vibrator, powerManager)
}