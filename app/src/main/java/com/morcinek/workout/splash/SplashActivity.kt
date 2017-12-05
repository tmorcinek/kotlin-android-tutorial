package com.morcinek.workout.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.morcinek.workout.common.di.component
import com.morcinek.workout.common.firebase.auth.UserManager
import com.morcinek.workout.home.HomeActivity
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject lateinit var userManager: UserManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)

        if (!userManager.isUserAuthenticated) {
            authenticateUser()
        } else {
            startNextActivity()
        }
    }

    private fun startNextActivity() {
        startActivity<HomeActivity>()
        finish()
    }

    private fun authenticateUser() {
        userManager.signInAnonymously().addOnCompleteListener {
            if (it.isSuccessful) {
                startNextActivity()
            } else {
                finish()
            }
        }
    }
}