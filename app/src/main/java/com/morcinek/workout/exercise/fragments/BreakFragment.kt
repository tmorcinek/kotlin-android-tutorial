package com.morcinek.workout.exercise.fragments

import android.content.IntentFilter
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import com.morcinek.workout.R
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.common.utils.broadcastReceiver
import com.morcinek.workout.common.utils.getLong
import com.morcinek.workout.exercise.ExerciseDataManager
import com.morcinek.workout.exercise.TIMER_SERVICE_TICK
import com.morcinek.workout.exercise.TimerService
import com.morcinek.workout.exercise.exerciseComponent
import kotlinx.android.synthetic.main.exercise_break.*
import org.jetbrains.anko.support.v4.startService
import javax.inject.Inject


class BreakFragment : BaseFragment() {

    override val layoutResourceId = R.layout.exercise_break

    @Inject lateinit var exerciseDataManager: ExerciseDataManager

    private val breakIntervalInMillis: Long
        get() = exerciseDataManager.breakIntervalSeconds * DateUtils.SECOND_IN_MILLIS

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
        startService<TimerService>()
    }

    override fun onResume() {
        super.onResume()
        activity.registerReceiver(broadcastReceiver, IntentFilter(TIMER_SERVICE_TICK))
    }

    override fun onPause() {
        super.onPause()
        activity.unregisterReceiver(broadcastReceiver)
    }

    private fun updateProgress(progress: Long) {
        timerText.text = "${progress / 1000}.${progress % 1000 / 100}"
        progressBar.progress = (breakIntervalInMillis - progress).toInt()
    }

    private val broadcastReceiver = broadcastReceiver {
        updateProgress(it.getLong())
    }
}