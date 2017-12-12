package com.morcinek.workout.exercise

import android.content.SharedPreferences
import com.morcinek.workout.core.data.exercises.ExerciseDataModel
import com.morcinek.workout.settings.breakTime

class ExerciseDataManager(private val sharedPreferences: SharedPreferences) {

    var exerciseState: ExerciseState = ExerciseState.Loading

    lateinit var exerciseDataModel: ExerciseDataModel

    var numberOfSeries: Int
        get() = exerciseDataModel.numberOfSeries
        set(value) {
            exerciseDataModel.numberOfSeries = value
        }

    val breakIntervalSeconds: Long
        get() = sharedPreferences.breakTime.toLong()

    var delegate: Delegate? = null
        set(value) {
            field = value
            notifyStateChanged()
        }

    fun incrementSeriesNumber() {
        numberOfSeries += 1
    }

    fun showNew() {
        exerciseState = ExerciseState.New
        notifyStateChanged()
    }

    fun showBreak() {
        exerciseState = ExerciseState.Break
        notifyStateChanged()
    }

    fun showBreakSplash() {
        exerciseState = ExerciseState.Splash
        notifyStateChanged()
    }

    fun showSeries() {
        exerciseState = ExerciseState.Series
        notifyStateChanged()
    }

    private fun notifyStateChanged() {
        delegate?.onStateChanged(exerciseState)
    }

    interface Delegate {
        fun onStateChanged(exerciseState: ExerciseState)
    }
    
    val isEditable: Boolean
        get() = exerciseState != ExerciseState.New
}