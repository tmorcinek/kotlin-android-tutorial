package com.morcinek.workout.exercise

class ExerciseData(var seriesNumber: Int = 1, var seriesState: ExerciseState = ExerciseState.Loading) {

    enum class ExerciseState {
        Series, Break, Splash, New, Loading
    }
}