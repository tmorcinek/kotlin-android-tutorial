package com.morcinek.workout.home.exercises

import android.text.format.DateUtils
import com.morcinek.workout.common.firebase.data.DataProvider
import com.morcinek.workout.common.firebase.data.InteractorDelegate
import com.morcinek.workout.common.utils.dayOfWeekFormat
import com.morcinek.workout.common.utils.formatWith
import com.morcinek.workout.common.utils.timeFormat
import com.morcinek.workout.core.data.exercises.ExerciseDataModel
import com.morcinek.workout.core.data.exercises.ExercisesProvider
import com.morcinek.workout.home.exercises.adapter.ExerciseViewModel
import java.util.*

class ExercisesInteractor(private val exercisesProvider: ExercisesProvider) : DataProvider.Delegate<ExerciseDataModel> {

    private val timeFormat by lazy { timeFormat() }
    private val dayOfWeekFormat by lazy { dayOfWeekFormat() }

    lateinit var delegate: InteractorDelegate<List<ExerciseViewModel>>

    fun register(delegate: InteractorDelegate<List<ExerciseViewModel>>) {
        this.delegate = delegate
        exercisesProvider.delegate = this
        exercisesProvider.register()
    }

    fun unregister() {
        exercisesProvider.unregister()
    }

    override fun failed(errorMessage: String) = delegate.failed(errorMessage)

    override fun success(values: List<Pair<String, ExerciseDataModel>>) = delegate.success(values.sortedByDescending { it.second.timeInMillis }.map {
        val date = it.second.date
        ExerciseViewModel(it.first, it.second.name, it.second.category ?: "", dateFormat(date), timeFormat(date))
    })

    private fun dateFormat(date: Calendar) = "${relativeDateFormat(date)}, ${dayOfWeekFormat(date)}"

    private fun dayOfWeekFormat(date: Calendar) = date.formatWith(dayOfWeekFormat)

    private fun timeFormat(date: Calendar) = date.formatWith(timeFormat)

    private fun relativeDateFormat(date: Calendar)
            = DateUtils.getRelativeTimeSpanString(date.timeInMillis, Date().time, DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_SHOW_YEAR).toString()
}