package com.morcinek.workout.common.di.modules

import com.morcinek.workout.common.firebase.database.ReferenceManager
import com.morcinek.workout.common.firebase.di.modules.FirebaseModule
import com.morcinek.workout.core.data.exercises.ExercisesManager
import com.morcinek.workout.core.data.exercises.ExercisesProvider
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(FirebaseModule::class))
class DataModule {

    @Provides
    fun provideExercisesProvider(referenceManager: ReferenceManager) = ExercisesProvider(referenceManager)

    @Provides
    fun provideExercisesManager(referenceManager: ReferenceManager) = ExercisesManager(referenceManager)
}
