package com.morcinek.workout.exercise.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import com.morcinek.workout.R
import com.morcinek.workout.common.FunctionalCountDownTimer
import com.morcinek.workout.common.NotificationCenter
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.exercise.COUNTDOWN_BR
import com.morcinek.workout.exercise.ExerciseDataManager
import com.morcinek.workout.exercise.TimerService
import com.morcinek.workout.exercise.exerciseComponent
import kotlinx.android.synthetic.main.exercise_break.*
import org.jetbrains.anko.support.v4.startService
import javax.inject.Inject


class BreakFragment : BaseFragment() {

    override val layoutResourceId = R.layout.exercise_break

    @Inject lateinit var exerciseDataManager: ExerciseDataManager
    @Inject lateinit var notificationCenter: NotificationCenter

    private val breakIntervalInMillis: Long
        get() = exerciseDataManager.breakIntervalSeconds * DateUtils.SECOND_IN_MILLIS

    private val timer by lazy {
        FunctionalCountDownTimer(breakIntervalInMillis, 100)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseComponent.inject(this)

        seriesNumberText.text = "${exerciseDataManager.exerciseData.seriesNumber}"
        startNewSeriesButton.setOnClickListener {
            exerciseDataManager.incrementSeriesNumber()
            exerciseDataManager.showSeries()
        }
        cancelButton.setOnClickListener {
            exerciseDataManager.showSeries()
        }
        progressBar.max = breakIntervalInMillis.toInt()
        setupTimer()
    }

    private fun setupTimer() {
//        timer.onTick {
//            updateProgress(it)
//        }
//        timer.onFinish {
//            updateProgress(0)
//            notificationCenter.sendNotifications()
//            exerciseDataManager.incrementSeriesNumber()
//            exerciseDataManager.showBreakSplash()
//        }
//        timer.start()
        startService<TimerService>()
    }

    override fun onResume() {
        super.onResume()
        activity.registerReceiver(broadcastReceiver, IntentFilter(COUNTDOWN_BR))
    }

    override fun onPause() {
        super.onPause()
        activity.unregisterReceiver(broadcastReceiver)
    }

    private fun updateProgress(progress: Long) {
        timerText.text = "${progress / 1000}.${progress % 1000 / 100}"
        progressBar.progress = (breakIntervalInMillis - progress).toInt()
    }

    private fun notifyTimerFinished() {
        notificationCenter.sendNotifications()
        exerciseDataManager.incrementSeriesNumber()
        exerciseDataManager.showBreakSplash()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val millisUntilFinished = intent.getLongExtra("countdown", 0)
            updateProgress(millisUntilFinished)
            if (millisUntilFinished == 0L) {
                notifyTimerFinished()
            }
        }
    }
}