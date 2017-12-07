package com.morcinek.workout.core.data.exercises

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties
import com.morcinek.workout.common.firebase.data.DataModel
import java.util.*

@IgnoreExtraProperties
class ExerciseDataModel(var name: String = "", var category: String? = null, var timeInMillis: Long = Calendar.getInstance().timeInMillis, var numberOfSeries: Int = 1) : DataModel {

    @Exclude
    override fun toMap() = mapOf(
            "name" to name,
            "category" to category,
            "timeInMillis" to timeInMillis,
            "numberOfSeries" to numberOfSeries
    )

    var date: Calendar
        @Exclude get() = Calendar.getInstance().apply { timeInMillis = this@ExerciseDataModel.timeInMillis }
        @Exclude set(value) {
            timeInMillis = value.timeInMillis
        }
}