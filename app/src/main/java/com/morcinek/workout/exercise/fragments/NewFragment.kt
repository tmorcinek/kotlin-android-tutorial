package com.morcinek.workout.exercise.fragments

import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import android.widget.ArrayAdapter
import com.morcinek.workout.R
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.common.utils.dateFormat
import com.morcinek.workout.common.utils.formatWith
import com.morcinek.workout.common.utils.timeFormat
import com.morcinek.workout.core.data.exercises.ExerciseDataModel
import com.morcinek.workout.core.data.exercises.ExerciseManager
import com.morcinek.workout.exercise.ExerciseDataManager
import com.morcinek.workout.exercise.exerciseComponent
import kotlinx.android.synthetic.main.exercise_new.*
import org.jetbrains.anko.sdk25.coroutines.onItemClick
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import java.util.*
import javax.inject.Inject


class NewFragment : BaseFragment() {

    override val layoutResourceId = R.layout.exercise_new

    @Inject lateinit var exerciseDataManager: ExerciseDataManager
    @Inject lateinit var exerciseManager: ExerciseManager

    private val dateFormat by lazy { dateFormat() }
    private val timeFormat by lazy { timeFormat() }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseComponent.inject(this)

        val exerciseDataModel = exerciseDataManager.exerciseDataModel
        nameEditText.setText(exerciseDataModel.name)
        nameEditText.textChangedListener {
            onTextChanged { charSequence, _, _, _ ->
                exerciseDataModel.name = charSequence.toString()
            }
        }
        setupCategorySpinner(exerciseDataModel)

        dateTextView.text = formatDate(exerciseDataModel.date)
        breakButton.setOnClickListener { exerciseDataManager.showBreak() }
        exerciseButton.setOnClickListener { exerciseDataManager.showSeries() }
    }

    private fun setupCategorySpinner(exerciseDataModel: ExerciseDataModel) {
        val adapter = ArrayAdapter.createFromResource(context, R.array.categories, android.R.layout.simple_spinner_item)
        categorySpinner.adapter = adapter
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.setSelection(adapter.getPosition(exerciseDataModel.category))
        categorySpinner.onItemSelectedListener {
            onItemSelected { _, _, position, _ -> exerciseDataModel.category = adapter.getItem(position).toString()  }
        }
    }

    private fun formatDate(date: Calendar) = "${timeFormat(date)}, ${relativeDateFormat(date)}"

    private fun timeFormat(date: Calendar) = date.formatWith(timeFormat)

    private fun relativeDateFormat(date: Calendar)
            = DateUtils.getRelativeTimeSpanString(date.timeInMillis, Date().time, DateUtils.DAY_IN_MILLIS, DateUtils.FORMAT_SHOW_YEAR).toString()

}