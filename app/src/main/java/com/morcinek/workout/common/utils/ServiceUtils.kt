package com.morcinek.workout.common.utils

import android.app.Activity
import android.app.Service
import android.content.Intent

inline fun <reified T : Service> Activity.stopService(){
    stopService(Intent(this, T::class.java))
}