package com.morcinek.workout.common.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.morcinek.workout.R
import com.morcinek.workout.common.utils.getSerializableExtra
import kotlinx.android.synthetic.main.content_fragment.*

class SingleFragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_outside_right)
        setContentView(R.layout.content_fragment)
        setupToolbar()
        if (savedInstanceState == null) {
            val classType = intent.getSerializableExtra<Class<Fragment>>()!!
            supportFragmentManager.beginTransaction().replace(R.id.contentFrame, classType.newInstance()).commit();
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun finish() {
        super.finish()
//        overridePendingTransition(R.anim.slide_inside_left, R.anim.slide_out_right)
    }
}