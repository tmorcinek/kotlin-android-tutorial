package com.morcinek.workout.exercise

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.morcinek.workout.R
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.common.fragment.ContentFragmentManager
import com.morcinek.workout.exercise.fragments.BreakFragment
import com.morcinek.workout.exercise.fragments.BreakSplashFragment
import com.morcinek.workout.exercise.fragments.SeriesFragment
import kotlinx.android.synthetic.main.exercise.*

class ExerciseActivity : AppCompatActivity(), ExerciseDataManager.Delegate {

    private val contentFragmentManager: ContentFragmentManager = ContentFragmentManager(this)
    val exerciseDataManager: ExerciseDataManager = ExerciseDataManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exercise)

        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onStateChanged(exerciseState: ExerciseData.ExerciseState) {
        contentFragmentManager.replaceFragment(when (exerciseState) {
            ExerciseData.ExerciseState.Series -> SeriesFragment()
            ExerciseData.ExerciseState.Break -> BreakFragment()
            ExerciseData.ExerciseState.Splash -> BreakSplashFragment()
        })
    }

    override fun onResume() {
        super.onResume()
        exerciseDataManager.delegate = this
    }

    override fun onPause() {
        super.onPause()
        exerciseDataManager.delegate = null
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}

inline val BaseFragment.exerciseDataManager: ExerciseDataManager
    get() = (activity as ExerciseActivity).exerciseDataManager