package com.morcinek.workout.common.firebase.data

import com.google.firebase.database.*

abstract class DataProvider<T> : ValueEventListener {

    lateinit var delegate: Delegate<T>

    protected abstract val reference: Query
    protected abstract val type: Class<T>

    fun register() {
        reference.addValueEventListener(this)
    }

    fun registerForSingleValue() {
        reference.addListenerForSingleValueEvent(this)
    }

    fun unregister() {
        reference.removeEventListener(this)
    }

    override fun onDataChange(dataSnapshot: DataSnapshot?) {
        delegate.success(dataSnapshot!!.children.map { it.key to it.getValue(type)!! })
    }

    override fun onCancelled(error: DatabaseError?) {
        delegate.failed(error!!.message)
    }

    interface Delegate<in T> {
        fun success(values: List<Pair<String, T>>)
        fun failed(errorMessage: String)
    }
}