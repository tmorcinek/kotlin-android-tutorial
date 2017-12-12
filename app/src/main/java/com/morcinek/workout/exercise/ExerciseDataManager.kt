package com.morcinek.workout.exercise

import android.content.SharedPreferences
import com.morcinek.workout.core.data.exercises.ExerciseDataModel
import com.morcinek.workout.settings.breakTime

class ExerciseDataManager(private val sharedPreferences: SharedPreferences) {

    var exerciseData: ExerciseData = ExerciseData()

    lateinit var exerciseDataModel : ExerciseDataModel

    val breakIntervalSeconds: Long
        get() = sharedPreferences.breakTime.toLong()

    var delegate: Delegate? = null
        set(value) {
            field = value
            notifyStateChanged()
        }

    fun incrementSeriesNumber() {
        exerciseData.seriesNumber += 1
    }

    fun showNew() {
        exerciseData.seriesState = ExerciseData.ExerciseState.New
        notifyStateChanged()
    }

    fun showLoading() {
        exerciseData.seriesState = ExerciseData.ExerciseState.Loading
        notifyStateChanged()
    }


    fun showBreak() {
        exerciseData.seriesState = ExerciseData.ExerciseState.Break
        notifyStateChanged()
    }

    fun showBreakSplash() {
        exerciseData.seriesState = ExerciseData.ExerciseState.Splash
        notifyStateChanged()
    }

    fun showSeries() {
        exerciseData.seriesState = ExerciseData.ExerciseState.Series
        notifyStateChanged()
    }

    private fun notifyStateChanged() {
        delegate?.onStateChanged(exerciseData.seriesState)
    }

    interface Delegate {
        fun onStateChanged(exerciseState: ExerciseData.ExerciseState)
    }

    val exerciseHasStarted: Boolean
        get() = (exerciseData.seriesNumber > 1) or (exerciseData.seriesState != ExerciseData.ExerciseState.New)
}