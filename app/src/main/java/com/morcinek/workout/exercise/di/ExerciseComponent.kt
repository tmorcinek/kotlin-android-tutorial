package com.morcinek.workout.exercise.di

import com.morcinek.workout.common.di.ActivityScope
import com.morcinek.workout.exercise.ExerciseActivity
import com.morcinek.workout.exercise.fragments.BreakFragment
import com.morcinek.workout.exercise.fragments.BreakSplashFragment
import com.morcinek.workout.exercise.fragments.SeriesFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = arrayOf(ExerciseModule::class))
interface ExerciseComponent {

    fun inject(activity: ExerciseActivity)

    fun inject(fragment: SeriesFragment)
    fun inject(fragment: BreakFragment)
    fun inject(fragment: BreakSplashFragment)
}