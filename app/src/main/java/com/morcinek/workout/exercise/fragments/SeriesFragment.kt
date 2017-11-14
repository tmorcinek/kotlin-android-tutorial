package com.morcinek.workout.exercise.fragments

import android.os.Bundle
import android.view.View
import com.morcinek.workout.R
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.exercise.ExerciseDataManager
import com.morcinek.workout.exercise.exerciseComponent
import kotlinx.android.synthetic.main.exercise_series.*
import javax.inject.Inject

class SeriesFragment : BaseFragment() {

    override val layoutResourceId = R.layout.exercise_series

    @Inject lateinit var exerciseDataManager: ExerciseDataManager

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseComponent.inject(this)

        seriesNumberText.text = "${exerciseDataManager.exerciseData.seriesNumber}"
        breakButton.setOnClickListener { exerciseDataManager.showBreak() }
        finishButton.setOnClickListener { activity.onBackPressed() }
    }
}