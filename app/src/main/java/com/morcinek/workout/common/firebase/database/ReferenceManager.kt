package com.morcinek.workout.common.firebase.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ReferenceManager(val firebaseAuth: FirebaseAuth, val firebaseDatabase: FirebaseDatabase) {

    init {
        firebaseDatabase.setPersistenceEnabled(true)
    }

    private val userId: String
        get() = firebaseAuth.currentUser!!.uid

    fun exercises() = firebaseDatabase.getReference("data")
            .child(userId)
            .child("exercises")
}