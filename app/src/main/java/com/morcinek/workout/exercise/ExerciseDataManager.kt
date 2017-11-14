package com.morcinek.workout.exercise

class ExerciseDataManager {

    var exerciseData: ExerciseData = ExerciseData()

    var delegate: Delegate? = null
        set(value) {
            field = value
            notifyStateChanged()
        }

    fun incrementSeriesNumber() {
        exerciseData.seriesNumber += 1
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
        get() = (exerciseData.seriesNumber > 1) or (exerciseData.seriesState != ExerciseData.ExerciseState.Series)
}