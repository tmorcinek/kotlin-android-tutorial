package com.morcinek.workout.exercise.fragments

import android.os.Bundle
import android.view.View
import com.morcinek.workout.R
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.common.utils.dateFormat
import com.morcinek.workout.common.utils.formatWith
import com.morcinek.workout.core.data.exercises.ExerciseManager
import com.morcinek.workout.exercise.ExerciseDataManager
import com.morcinek.workout.exercise.exerciseComponent
import kotlinx.android.synthetic.main.exercise_new.*
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import java.util.*
import javax.inject.Inject

class NewFragment : BaseFragment() {

    override val layoutResourceId = R.layout.exercise_new

    @Inject lateinit var exerciseDataManager: ExerciseDataManager
    @Inject lateinit var exerciseManager: ExerciseManager

    private val dateFormat by lazy { dateFormat() }

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
        dateTextView.text = formatDate(exerciseDataModel.date)
        breakButton.setOnClickListener { exerciseDataManager.showBreak() }
        exerciseButton.setOnClickListener { exerciseDataManager.showSeries() }
    }

    private fun formatDate(date: Calendar) = date.formatWith(dateFormat)
}