package com.morcinek.workout.core.data.exercises

import com.morcinek.workout.common.firebase.data.DataManagerImpl
import com.morcinek.workout.common.firebase.database.ReferenceManager

class ExerciseManager(referenceManager: ReferenceManager) : DataManagerImpl(referenceManager.exercises()){

    fun setName(name: String) = get().child("name").setValue(name)
    fun setCategory(category: String) = get().child("category").setValue(category)
    fun setNumberOfSeries(numberOfSeries: Int) = get().child("numberOfSeries").setValue(numberOfSeries)
}