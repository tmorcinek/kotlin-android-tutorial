package com.morcinek.workout.exercise.fragments

import android.os.Bundle
import android.view.View
import com.morcinek.workout.R
import com.morcinek.workout.common.fragment.BaseFragment
import kotlinx.android.synthetic.main.exercise_break.*

class BreakFragment: BaseFragment() {

    override val layoutResourceId = R.layout.exercise_break

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seriesNumberText.text = "1"
        startNewSeriesButton.setOnClickListener {
            // TODO increment Series number
            // TODO show SeriesFragment
        }
        cancelButton.setOnClickListener {
            // TODO show SeriesFragment
        }
    }
}