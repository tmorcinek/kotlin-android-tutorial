package com.morcinek.workout.core.data

import com.morcinek.workout.common.firebase.database.ReferenceManager
import com.morcinek.workout.common.firebase.di.modules.FirebaseModule
import com.morcinek.workout.core.data.exercises.ExercisesProvider
import com.morcinek.workout.home.exercises.ExercisesInteractor
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(FirebaseModule::class))
class DataModule {

    @Provides
    fun provideExercisesProvider(referenceManager: ReferenceManager) = ExercisesProvider(referenceManager)

    @Provides
    fun provideExercisesInteractor(exercisesProvider: ExercisesProvider) = ExercisesInteractor(exercisesProvider)
}
