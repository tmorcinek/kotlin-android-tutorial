package com.morcinek.workout.exercise

import android.content.IntentFilter
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.morcinek.workout.R
import com.morcinek.workout.common.NotificationCenter
import com.morcinek.workout.common.di.component
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.common.fragment.ContentFragmentManager
import com.morcinek.workout.common.fragment.SingleFragmentActivity
import com.morcinek.workout.common.utils.*
import com.morcinek.workout.core.data.exercises.ExerciseManager
import com.morcinek.workout.core.data.getKeyExtra
import com.morcinek.workout.exercise.di.ExerciseComponent
import com.morcinek.workout.exercise.di.ExerciseModule
import com.morcinek.workout.exercise.fragments.*
import com.morcinek.workout.settings.SettingsFragment
import kotlinx.android.synthetic.main.content_fragment.*
import org.jetbrains.anko.alert
import javax.inject.Inject

class ExerciseActivity : AppCompatActivity(), ExerciseDataManager.Delegate {

    @Inject lateinit var contentFragmentManager: ContentFragmentManager

    @Inject lateinit var exerciseDataManager: ExerciseDataManager
    @Inject lateinit var exerciseManager: ExerciseManager
    @Inject lateinit var notificationCenter: NotificationCenter

    val key by lazy { intent.getKeyExtra() }

    val exerciseComponent by lazy { component.add(ExerciseModule(this)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_fragment)
        exerciseComponent.inject(this)
        setupToolbar()
        registerReceiver(timerReceiver, IntentFilter(TIMER_SERVICE_FINISH))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timerReceiver)
        stopService<TimerService>()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.exercise, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.action_edit)?.isVisible = exerciseDataManager.isEditable
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onStateChanged(exerciseState: ExerciseState) {
        contentFragmentManager.replaceFragment(when (exerciseState) {
            ExerciseState.Series -> SeriesFragment()
            ExerciseState.Break -> BreakFragment()
            ExerciseState.Splash -> BreakSplashFragment()
            ExerciseState.New -> NewFragment()
            ExerciseState.Loading -> LoadingFragment()
        })
        invalidateOptionsMenu()
    }

    override fun onResume() {
        super.onResume()
        exerciseDataManager.delegate = this
    }

    override fun onPause() {
        super.onPause()
        exerciseDataManager.delegate = null
    }

    private val timerReceiver = broadcastReceiver {
        notificationCenter.sendNotifications()
        exerciseDataManager.incrementSeriesNumber()
        exerciseDataManager.showBreakSplash()
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> handleOptionItemAction { onBackPressed() }
        R.id.action_edit -> handleOptionItemAction { exerciseDataManager.showNew() }
        R.id.action_delete -> handleOptionItemAction {
            alert(R.string.exercise_exit_message) {
                positiveButton(R.string.yes) { finishWithDelete() }
                negativeButton(R.string.no) { }
            }.show()
        }
        R.id.action_settings -> handleOptionItemAction {
            startActivityFun<SingleFragmentActivity> { putSerializableExtra(SettingsFragment::class.java) }
        }

        else -> super.onOptionsItemSelected(item)
    }


    override fun onBackPressed() {
        if (key == null) {
            alert(R.string.exercise_exit_message) {
                positiveButton(R.string.yes) { saveAndFinish() }
                negativeButton(R.string.no) { finishWithDelete() }
            }.show()
        } else {
            saveAndFinish()
        }
    }

    private fun finishWithDelete() {
        exerciseManager.remove()
        finish()
    }

    private fun saveAndFinish() {
        exerciseManager.update(exerciseDataManager.exerciseDataModel)
        finish()
    }
}

inline val BaseFragment.exerciseComponent: ExerciseComponent
    get() = (activity as ExerciseActivity).exerciseComponent
