package com.morcinek.workout.exercise

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.morcinek.workout.R
import com.morcinek.workout.common.di.component
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.common.fragment.ContentFragmentManager
import com.morcinek.workout.common.fragment.SingleFragmentActivity
import com.morcinek.workout.common.utils.putSerializableExtra
import com.morcinek.workout.common.utils.startActivityFun
import com.morcinek.workout.common.utils.stopService
import com.morcinek.workout.core.data.exercises.ExerciseDataModel
import com.morcinek.workout.core.data.exercises.ExercisesManager
import com.morcinek.workout.exercise.di.ExerciseComponent
import com.morcinek.workout.exercise.di.ExerciseModule
import com.morcinek.workout.exercise.fragments.BreakFragment
import com.morcinek.workout.exercise.fragments.BreakSplashFragment
import com.morcinek.workout.exercise.fragments.NewFragment
import com.morcinek.workout.exercise.fragments.SeriesFragment
import com.morcinek.workout.settings.SettingsFragment
import kotlinx.android.synthetic.main.content_fragment.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton
import javax.inject.Inject

class ExerciseActivity : AppCompatActivity(), ExerciseDataManager.Delegate, OnCompleteListener<Void>, ValueEventListener {

    @Inject lateinit var contentFragmentManager: ContentFragmentManager
    @Inject lateinit var exerciseDataManager: ExerciseDataManager
    @Inject lateinit var exercisesManager: ExercisesManager

    val exerciseComponent by lazy {
        component.add(ExerciseModule(this))
    }

    private var exerciseDataModel = ExerciseDataModel("Exercise 1")
        set(value) {
            field = value
            updateUI()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_fragment)
        exerciseComponent.inject(this)
        setupToolbar()

//        exercisesManager.update(exerciseDataModel).addOnCompleteListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService<TimerService>()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun updateUI() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onStateChanged(exerciseState: ExerciseData.ExerciseState) {
        contentFragmentManager.replaceFragment(when (exerciseState) {
            ExerciseData.ExerciseState.Series -> SeriesFragment()
            ExerciseData.ExerciseState.Break -> BreakFragment()
            ExerciseData.ExerciseState.Splash -> BreakSplashFragment()
            ExerciseData.ExerciseState.New -> NewFragment()
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
        R.id.action_settings -> {
            startActivityFun<SingleFragmentActivity> { putSerializableExtra(SettingsFragment::class.java) }
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (exerciseDataManager.exerciseHasStarted) {
            alert(R.string.exercise_exit_message) {
                yesButton { super.onBackPressed() }
                noButton { }
            }.show()
        } else {
            super.onBackPressed()
        }
    }

    override fun onComplete(p0: Task<Void>) {
        if (!p0.isSuccessful) {
            snackbar(rootView, p0.exception?.localizedMessage ?: "")
            exercisesManager.get().addListenerForSingleValueEvent(this)
        }
    }

    override fun onCancelled(p0: DatabaseError?) {
        snackbar(rootView, p0?.message ?: "")
    }

    override fun onDataChange(p0: DataSnapshot?) {
        exerciseDataModel = p0!!.getValue(ExerciseDataModel::class.java)!!
    }
}

inline val BaseFragment.exerciseComponent: ExerciseComponent
    get() = (activity as ExerciseActivity).exerciseComponent