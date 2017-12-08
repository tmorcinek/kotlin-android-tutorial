package com.morcinek.workout.exercise

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.CountDownTimer
import android.util.Log


val COUNTDOWN_BR = "com.morcinek.workout.countdown_br"

class TimerService() : Service() {

    private val TAG = "BroadcastService"


    var bi = Intent(COUNTDOWN_BR)
    var cdt: CountDownTimer? = null

    override fun onCreate() {
        super.onCreate()

        Log.i(TAG, "Starting timer...")

        cdt = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

                Log.i(TAG, "Countdown seconds remaining: " + millisUntilFinished / 1000)
                bi.putExtra("countdown", millisUntilFinished)
                sendBroadcast(bi)
            }

            override fun onFinish() {
                Log.i(TAG, "Timer finished")
                sendBroadcast(bi)
            }
        }
        cdt!!.start()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.i(TAG, "onStartCommand...")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        cdt!!.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?) = null

}