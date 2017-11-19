package com.morcinek.workout.common.di.modules

import android.content.Context
import android.content.SharedPreferences
import android.os.PowerManager
import android.os.Vibrator
import com.morcinek.workout.common.NotificationCenter
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule() {

    @Provides
    fun provideNotificationCenter(context: Context, vibrator: Vibrator, powerManager: PowerManager, sharedPreferences: SharedPreferences)
            = NotificationCenter(context, vibrator, powerManager, sharedPreferences)
}