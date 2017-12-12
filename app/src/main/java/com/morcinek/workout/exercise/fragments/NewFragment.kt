package com.morcinek.workout.exercise.fragments

import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.widget.ArrayAdapter
import com.morcinek.workout.R
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.common.utils.formatWith
import com.morcinek.workout.common.utils.timeFormat
import com.morcinek.workout.core.data.exercises.ExerciseDataModel
import com.morcinek.workout.exercise.ExerciseDataManager
import com.morcinek.workout.exercise.exerciseComponent
import kotlinx.android.synthetic.main.exercise_new.*
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import java.util.*
import javax.inject.Inject


class NewFragment : BaseFragment() {

    override val layoutResourceId = R.layout.exercise_new

    @Inject lateinit var exerciseDataManager: ExerciseDataManager

    private val timeFormat by lazy { timeFormat() }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseComponent.inject(this)

        val exerciseDataModel = exerciseDataManager.exerciseDataModel
        setupName(exerciseDataModel)
        setupDate(exerciseDataModel)
        setupCategory(exerciseDataModel)

        breakButton.setOnClickListener { exerciseDataManager.showBreak() }
        exerciseButton.setOnClickListener { exerciseDataManager.showSeries() }
    }

    private fun setupName(exerciseDataModel: ExerciseDataModel) {
        nameEditText.setText(exerciseDataModel.name)
        nameEditText.textChangedListener {
            onTextChanged { charSequence, _, _, _ ->
                exerciseDataModel.name = charSequence.toString()
            }
        }
    }

    private fun setupDate(exerciseDataModel: ExerciseDataModel) {
        dateTextView.text = formatDate(exerciseDataModel.date)
    }

    private fun formatDate(date: Calendar) = "${relativeDateFormat(date)}, ${date.formatWith(timeFormat)}"

    private fun relativeDateFormat(date: Calendar)
            = DateUtils.getRelativeTimeSpanString(date.timeInMillis, Date().time, DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_SHOW_YEAR).toString()

    private fun setupCategory(exerciseDataModel: ExerciseDataModel) {
        val adapter = ArrayAdapter.createFromResource(context, R.array.categories, R.layout.spinner_layout)
        categorySpinner.adapter = adapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.setSelection(adapter.getPosition(exerciseDataModel.category))
        categorySpinner.onItemSelectedListener {
            onItemSelected { _, _, position, _ -> exerciseDataModel.category = adapter.getItem(position).toString() }
        }
    }
}