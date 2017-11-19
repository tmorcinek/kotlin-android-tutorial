package com.morcinek.workout.settings

import android.content.SharedPreferences
import com.morcinek.workout.common.utils.getBoolean
import com.morcinek.workout.common.utils.getInt


const val BREAK_TIME = "generalBreakTime"
const val VIBRATION_ENABLED = "notificationVibrationEnabled"
const val VIBRATION_VALUE = "notificationVibrationValue"
const val SOUND_ENABLED = "notificationSoundEnabled"

inline val SharedPreferences.breakTime: Int
    get() = getInt(BREAK_TIME)
//    set(value) = applyFunction { putInt(BREAK_TIME, value) }

inline val SharedPreferences.vibrationEnabled: Boolean
    get() = getBoolean(VIBRATION_ENABLED)
//    set(value) = applyFunction { putBoolean(VIBRATION_ENABLED, value) }

inline val SharedPreferences.vibrationValue: Int
    get() = getInt(VIBRATION_VALUE)
//    set(value) = applyFunction { putInt(VIBRATION_VALUE, value) }

inline val SharedPreferences.soundEnabled: Boolean
    get() = getBoolean(SOUND_ENABLED)
//    set(value) = applyFunction { putBoolean(SOUND_ENABLED, value) }

