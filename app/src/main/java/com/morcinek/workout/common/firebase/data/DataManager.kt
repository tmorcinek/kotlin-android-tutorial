package com.morcinek.workout.common.firebase.data

import com.google.firebase.database.DatabaseReference

abstract class DataManager {

    protected abstract val reference: DatabaseReference

    var key: String? = null

    private val objectReference by lazy { if (key != null) reference.child(key) else reference.push() }

    fun update(data: DataModel) = objectReference.updateChildren(data.toMap())

    fun remove() = objectReference.removeValue()

    fun get() = objectReference
}