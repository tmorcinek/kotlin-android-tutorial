package com.morcinek.workout.exercise

import android.app.Service
import android.content.Intent
import android.content.SharedPreferences
import android.text.format.DateUtils
import com.morcinek.workout.common.FunctionalCountDownTimer
import com.morcinek.workout.common.di.component
import com.morcinek.workout.common.utils.put
import com.morcinek.workout.settings.breakTime
import javax.inject.Inject


val TIMER_SERVICE_TICK = "com.morcinek.workout.TimerService.Tick"
val TIMER_SERVICE_FINISH = "com.morcinek.workout.TimerService.Finish"

class TimerService : Service() {

    @Inject lateinit var  sharedPreferences: SharedPreferences

    private val breakIntervalInMillis: Long
        get() = sharedPreferences.breakTime * DateUtils.SECOND_IN_MILLIS

    private var timer : FunctionalCountDownTimer? = null

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (timer == null) {
            timer = FunctionalCountDownTimer(breakIntervalInMillis, 100).apply {
                onTick { sendBroadcast(Intent(TIMER_SERVICE_TICK).apply { put(it) }) }
                onFinish {
                    sendBroadcast(Intent(TIMER_SERVICE_FINISH))
                    timer = null
                }
                start()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        timer?.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?) = null
}