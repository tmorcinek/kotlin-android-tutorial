package com.morcinek.workout.exercise

class ExerciseData(var seriesNumber: Int = 1, var seriesState: ExerciseState = ExerciseState.Series) {

    enum class ExerciseState{
        Series, Break, Splash, New
    }
}