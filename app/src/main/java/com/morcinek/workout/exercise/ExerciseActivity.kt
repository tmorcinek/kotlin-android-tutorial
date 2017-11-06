package com.morcinek.workout.exercise

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.morcinek.workout.R
import com.morcinek.workout.common.fragment.ContentFragmentManager
import com.morcinek.workout.exercise.fragments.SeriesFragment
import kotlinx.android.synthetic.main.exercise.*

class ExerciseActivity : AppCompatActivity() {

    private val contentFragmentManager : ContentFragmentManager = ContentFragmentManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.exercise)

        setupToolbar()

        contentFragmentManager.replaceFragment(SeriesFragment())
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}