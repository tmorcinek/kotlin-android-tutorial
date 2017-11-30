package com.morcinek.workout.core.data.exercises

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.morcinek.workout.common.firebase.data.DataModel
import java.util.*

@IgnoreExtraProperties
class ExerciseDataModel(val name: String, val category: String, val date: Calendar = Calendar.getInstance(), val numberOfSeries: Int) : DataModel {

    @Exclude
    override fun toMap() = mapOf(
            "name" to name,
            "category" to category,
            "date" to date,
            "numberOfSeries" to numberOfSeries
    )
}