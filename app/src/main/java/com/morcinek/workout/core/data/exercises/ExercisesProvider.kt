package com.morcinek.workout.core.data.exercises

import com.morcinek.workout.common.firebase.data.DataProvider
import com.morcinek.workout.common.firebase.database.ReferenceManager

class ExercisesProvider(private val referenceManager: ReferenceManager) : DataProvider<ExerciseDataModel>() {

    override val type = ExerciseDataModel::class.java

    override val reference by lazy { referenceManager.exercises() }
}