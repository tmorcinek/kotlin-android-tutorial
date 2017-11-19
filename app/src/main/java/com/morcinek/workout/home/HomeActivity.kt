package com.morcinek.workout.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.morcinek.workout.R
import com.morcinek.workout.common.di.component
import com.morcinek.workout.common.fragment.SingleFragmentActivity
import com.morcinek.workout.common.utils.putSerializableExtra
import com.morcinek.workout.common.utils.startActivityFun
import com.morcinek.workout.exercise.ExerciseActivity
import com.morcinek.workout.settings.SettingsFragment
import kotlinx.android.synthetic.main.home.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class HomeActivity : AppCompatActivity() {

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
            startActivityFun<SingleFragmentActivity> {
                putSerializableExtra(SettingsFragment::class.java)
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
