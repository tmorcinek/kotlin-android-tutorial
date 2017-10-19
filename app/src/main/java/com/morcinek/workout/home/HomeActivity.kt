package com.morcinek.workout.home

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v7.app.AppCompatActivity
import android.text.format.DateUtils
import android.view.Menu
import android.view.MenuItem
import com.morcinek.workout.R
import com.morcinek.workout.common.di.component
import kotlinx.android.synthetic.main.content_home.*
import kotlinx.android.synthetic.main.home.*
import org.jetbrains.anko.toast
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        component.inject(this)

        setSupportActionBar(toolbar)

        val timer = object : CountDownTimer(DateUtils.SECOND_IN_MILLIS * 5, DateUtils.SECOND_IN_MILLIS) {
            override fun onFinish() {
                timerText.text = "Count Down Finished"
            }

            override fun onTick(millisUntilFinished: Long) {
                timerText.text = "Count Down: ${millisUntilFinished / 1000}"
            }
        }

        fab.setOnClickListener {
            timer.start()
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
