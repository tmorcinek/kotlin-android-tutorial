package com.morcinek.workout.common.firebase.data

interface InteractorDelegate<in T> {
    fun success(values: T)
    fun failed(errorMessage: String)
}