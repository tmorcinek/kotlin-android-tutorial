package com.morcinek.workout.common.firebase.data

import com.google.firebase.database.DatabaseReference

abstract class DataManager {

    protected abstract val reference: DatabaseReference

    fun add(data: DataModel) = reference.push().updateChildren(data.toMap())

    fun update(key: String, data: DataModel) = reference.child(key).updateChildren(data.toMap())

    fun remove(key: String) = reference.child(key).removeValue()
}