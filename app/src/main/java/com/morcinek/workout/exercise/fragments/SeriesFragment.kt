package com.morcinek.workout.exercise.fragments

import android.os.Bundle
import android.view.View
import com.morcinek.workout.R
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.exercise.exerciseDataManager
import kotlinx.android.synthetic.main.exercise_series.*

class SeriesFragment : BaseFragment() {

    override val layoutResourceId = R.layout.exercise_series

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seriesNumberText.text = "1"
        breakButton.setOnClickListener { exerciseDataManager.showBreak() }
        finishButton.setOnClickListener { activity.onBackPressed() }
    }
}