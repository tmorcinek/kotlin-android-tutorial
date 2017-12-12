package com.morcinek.workout.exercise.di

import android.content.SharedPreferences
import android.support.v4.app.FragmentActivity
import com.morcinek.workout.common.di.ActivityScope
import com.morcinek.workout.common.firebase.database.ReferenceManager
import com.morcinek.workout.common.fragment.ContentFragmentManager
import com.morcinek.workout.core.data.exercises.ExerciseManager
import com.morcinek.workout.exercise.ExerciseDataManager
import dagger.Module
import dagger.Provides

@Module
class ExerciseModule(private val activity: FragmentActivity) {

    @Provides
    @ActivityScope
    fun provideExerciseDataManager(sharedPreferences: SharedPreferences) = ExerciseDataManager(sharedPreferences)

    @Provides
    @ActivityScope
    fun provideExerciseManager(referenceManager: ReferenceManager) = ExerciseManager(referenceManager)

    @Provides
    fun provideContentFragmentManager() = ContentFragmentManager(activity)
}