package com.morcinek.workout.exercise.fragments

import android.os.Bundle
import android.view.View
import com.morcinek.workout.R
import com.morcinek.workout.common.fragment.BaseFragment
import kotlinx.android.synthetic.main.exercise_break_splash.*

class BreakSplashFragment : BaseFragment() {

    override val layoutResourceId = R.layout.exercise_break_splash

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        breakSplashText.text = getString(R.string.break_splash_series_text, 1)
        view?.setOnClickListener {
            // TODO show SeriesFragment
        }
    }
}