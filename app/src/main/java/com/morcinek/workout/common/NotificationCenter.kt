package com.morcinek.workout.common

import android.content.Context
import android.content.SharedPreferences
import android.media.RingtoneManager
import android.os.PowerManager
import android.os.Vibrator
import com.morcinek.workout.settings.soundEnabled
import com.morcinek.workout.settings.vibrationEnabled
import com.morcinek.workout.settings.vibrationValue

class NotificationCenter(private val context: Context, private val vibrator: Vibrator,
                         private val powerManager: PowerManager, private val sharedPreferences: SharedPreferences) {

    private val flags: Int
        get() = PowerManager.SCREEN_BRIGHT_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP

    private val ringtone by lazy {
        RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
    }

    fun sendNotifications() {
        if (sharedPreferences.soundEnabled) {
            ringtone.play()
        }

        if (sharedPreferences.vibrationEnabled) {
            vibrator.vibrate(vibrationTime())
        }

        powerManager.newWakeLock(flags, javaClass.name).apply {
            acquire(1000)
        }
    }

    private fun vibrationTime() = sharedPreferences.vibrationValue * 200L
}
