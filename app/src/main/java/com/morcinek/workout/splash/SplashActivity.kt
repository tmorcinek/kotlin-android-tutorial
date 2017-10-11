package com.morcinek.workout.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.morcinek.workout.HomeActivity
import com.morcinek.workout.R
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.uiThread

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)

        doAsync {
            Thread.sleep(2000)
            uiThread {
                startActivity<HomeActivity>()
            }
        }
    }
}