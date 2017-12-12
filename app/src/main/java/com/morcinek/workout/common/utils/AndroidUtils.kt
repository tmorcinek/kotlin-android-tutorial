package com.morcinek.workout.common.utils

import android.app.Activity
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

inline fun <reified T : Service> Activity.stopService() {
    stopService(Intent(this, T::class.java))
}

inline fun broadcastReceiver(crossinline onReceiveFunction: (Intent) -> Unit) = object : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) = onReceiveFunction(intent)
}

fun handleOptionItemAction(function: () -> Any): Boolean {
    function()
    return true
}
