package com.morcinek.workout.core.data.exercises

import com.morcinek.workout.common.firebase.data.DataManagerImpl
import com.morcinek.workout.common.firebase.database.ReferenceManager

class ExercisesManager(referenceManager: ReferenceManager) : DataManagerImpl(referenceManager.exercises())