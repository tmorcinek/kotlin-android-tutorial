package com.morcinek.workout.common.firebase.auth

import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class UserManager(private val firebaseAuth: FirebaseAuth) {

    val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    val isUserAuthenticated: Boolean
        get() = currentUser != null

    fun signInAnonymously() = firebaseAuth.signInAnonymously()

    fun connectWithCredentials(authCredential: AuthCredential) =
            if (isUserAuthenticated) {
                currentUser!!.linkWithCredential(authCredential)
            } else {
                firebaseAuth.signInWithCredential(authCredential)
            }
}