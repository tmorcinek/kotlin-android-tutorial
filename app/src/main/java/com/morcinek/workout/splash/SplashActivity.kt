package com.morcinek.workout.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.morcinek.workout.home.HomeActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity<HomeActivity>()
        finish()
    }
}