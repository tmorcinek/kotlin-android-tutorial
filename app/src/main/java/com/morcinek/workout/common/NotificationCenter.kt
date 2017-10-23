package com.morcinek.workout.common

import android.content.Context
import android.media.RingtoneManager
import android.os.PowerManager
import android.os.Vibrator

class NotificationCenter(private val context: Context, private val vibrator: Vibrator, private val powerManager: PowerManager) {

    private val flags: Int
        get() = PowerManager.SCREEN_BRIGHT_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP

    private val ringtone by lazy {
        RingtoneManager.getRingtone(context, RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
    }

    fun sendNotifications() {
        // Ringtone
        ringtone.play()

        // Vibration
        vibrator.vibrate(500)

        // Unlock Screen
        powerManager.newWakeLock(flags, javaClass.name).apply {
            acquire()
            release()
        }
    }
}
