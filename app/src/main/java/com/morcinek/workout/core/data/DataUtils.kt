package com.morcinek.workout.core.data

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.morcinek.workout.common.utils.putParcelableExtra

fun Intent.putKeyExtra(value: String) = putExtra("key", value)
fun Intent.getKeyExtra() : String? = getStringExtra("key")

fun Bundle.putKey(value: String) = putString("key", value)

inline fun <reified T : Parcelable> Intent.putPair(pair: Pair<String, T>) {
    putKeyExtra(pair.first)
    putParcelableExtra(pair.second)
}