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
import com.morcinek.workout.common.utils.broadcastReceiver
import com.morcinek.workout.common.utils.putSerializableExtra
import com.morcinek.workout.common.utils.startActivityFun
import com.morcinek.workout.common.utils.stopService
import com.morcinek.workout.core.data.exercises.ExerciseManager
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
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onStateChanged(exerciseState: ExerciseData.ExerciseState) {
        contentFragmentManager.replaceFragment(when (exerciseState) {
            ExerciseData.ExerciseState.Series -> SeriesFragment()
            ExerciseData.ExerciseState.Break -> BreakFragment()
            ExerciseData.ExerciseState.Splash -> BreakSplashFragment()
            ExerciseData.ExerciseState.New -> NewFragment()
            ExerciseData.ExerciseState.Loading -> LoadingFragment()
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

    private val timerReceiver = broadcastReceiver {
        notificationCenter.sendNotifications()
        exerciseDataManager.incrementSeriesNumber()
        exerciseDataManager.showBreakSplash()
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
        if (!exerciseDataManager.exerciseHasStarted) {
            alert(R.string.exercise_exit_message) {
                positiveButton(R.string.yes) { saveAndFinish() }
                negativeButton(R.string.no) { finishWithDelete() }
                neutralPressed(android.R.string.cancel) {  }
            }.show()
        } else {
            saveAndFinish()
        }
    }

    private fun finishWithDelete() {
        exerciseManager.remove().addOnCompleteListener { finish() }
    }

    private fun saveAndFinish() {
        exerciseManager.update(exerciseDataManager.exerciseDataModel).addOnCompleteListener { finish() }
    }
}

inline val BaseFragment.exerciseComponent: ExerciseComponent
    get() = (activity as ExerciseActivity).exerciseComponent