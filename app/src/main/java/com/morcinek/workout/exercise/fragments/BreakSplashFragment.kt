package com.morcinek.workout.exercise.fragments

import android.os.Bundle
import android.view.View
import com.morcinek.workout.R
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.exercise.ExerciseDataManager
import com.morcinek.workout.exercise.exerciseComponent
import kotlinx.android.synthetic.main.exercise_break_splash.*
import javax.inject.Inject

class BreakSplashFragment : BaseFragment() {

    override val layoutResourceId = R.layout.exercise_break_splash

    @Inject lateinit var exerciseDataManager: ExerciseDataManager

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseComponent.inject(this)

        breakSplashText.text = getString(R.string.break_splash_series_text, exerciseDataManager.numberOfSeries)
        view?.setOnClickListener {
            exerciseDataManager.showSeries()
        }
    }
}