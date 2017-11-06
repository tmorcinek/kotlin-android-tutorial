package com.morcinek.workout.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.format.DateUtils
import android.view.Menu
import android.view.MenuItem
import com.morcinek.workout.R
import com.morcinek.workout.common.FunctionalCountDownTimer
import com.morcinek.workout.common.NotificationCenter
import com.morcinek.workout.common.di.component
import com.morcinek.workout.exercise.ExerciseActivity
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject lateinit var notificationCenter: NotificationCenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        component.inject(this)

        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            startActivity<ExerciseActivity>()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.action_settings -> {
            toast("Settings Action")
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
