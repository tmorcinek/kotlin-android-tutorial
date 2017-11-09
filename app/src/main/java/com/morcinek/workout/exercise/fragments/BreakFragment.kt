package com.morcinek.workout.exercise.fragments

import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import com.morcinek.workout.R
import com.morcinek.workout.common.FunctionalCountDownTimer
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.exercise.exerciseDataManager
import kotlinx.android.synthetic.main.exercise_break.*

class BreakFragment : BaseFragment() {

    override val layoutResourceId = R.layout.exercise_break

    private val breakIntervalInMillis: Long
        get() = exerciseDataManager.exerciseData.breakIntervalSeconds * DateUtils.SECOND_IN_MILLIS

    private val timer by lazy {
        FunctionalCountDownTimer(breakIntervalInMillis, 100)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        timer.onTick {
            updateProgress(it)
        }
        timer.onFinish {
            updateProgress(0)
            //TODO sendNotifications
            exerciseDataManager.incrementSeriesNumber()
            exerciseDataManager.showBreakSplash()
        }
        timer.start()
    }

    private fun updateProgress(progress: Long) {
        timerText.text = "${progress / 1000}.${progress % 1000 / 100}"
        progressBar.progress = (breakIntervalInMillis - progress).toInt()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}