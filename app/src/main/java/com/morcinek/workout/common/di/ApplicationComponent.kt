package com.morcinek.workout.common.di

import com.morcinek.workout.common.di.modules.AndroidModule
import com.morcinek.workout.common.di.modules.ApplicationModule
import com.morcinek.workout.common.di.modules.DataModule
import com.morcinek.workout.common.firebase.di.modules.FirebaseModule
import com.morcinek.workout.exercise.di.ExerciseComponent
import com.morcinek.workout.exercise.di.ExerciseModule
import com.morcinek.workout.home.HomeActivity
import com.morcinek.workout.home.exercises.ExercisesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidModule::class, ApplicationModule::class, FirebaseModule::class, DataModule::class))
interface ApplicationComponent {

    fun inject(activity: HomeActivity)
    fun inject(fragment: ExercisesFragment)

    fun add(exerciseModule: ExerciseModule): ExerciseComponent
}