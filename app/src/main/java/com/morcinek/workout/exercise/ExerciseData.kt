package com.morcinek.workout.exercise

class ExerciseData(var seriesState: ExerciseState = ExerciseState.Loading) {

    enum class ExerciseState {
        Series, Break, Splash, New, Loading
    }
}