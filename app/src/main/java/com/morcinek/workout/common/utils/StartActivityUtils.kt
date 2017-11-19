package com.morcinek.workout.common.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import org.jetbrains.anko.internals.AnkoInternals

inline fun <reified T : Activity> Context.startActivity(vararg params: Any) {
    val arrayOfPairs = params.map { Pair(it.javaClass.name, it) }.toTypedArray()
    AnkoInternals.internalStartActivity(this, T::class.java, arrayOfPairs)
}

inline fun <reified T : Activity> Activity.startActivityFun(function: Intent.() -> Any) {
    val intent = AnkoInternals.createIntent(this, T::class.java, emptyArray())
    function(intent)
    startActivity(intent)
}
