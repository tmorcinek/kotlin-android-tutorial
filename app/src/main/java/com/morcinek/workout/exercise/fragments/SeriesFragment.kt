package com.morcinek.workout.exercise.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morcinek.workout.R
import kotlinx.android.synthetic.main.exercise_series.*

class SeriesFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater?.inflate(R.layout.exercise_series, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        seriesNumberText.text = "1"
        breakButton.setOnClickListener { }
        finishButton.setOnClickListener { activity.onBackPressed() }
    }
}