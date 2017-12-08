package com.morcinek.workout.common.utils

import android.content.Intent
import android.os.Parcelable
import java.io.Serializable

inline fun <reified T : Serializable> Intent.getSerializableExtra() = getSerializableExtra(T::class.java.name) as T?

fun <T : Parcelable> Intent.putParcelableExtra(value: T) = putExtra(value.javaClass.getName(), value)
fun <T : Serializable> Intent.putSerializableExtra(value: T) = putExtra(value.javaClass.getName(), value)

fun Intent.getString() = getStringExtra(String::class.java.name)
fun Intent.put(value: String) = putExtra(String::class.java.name, value)

fun Intent.getFloat() = getFloatExtra(Float::class.java.name, 0F)
fun Intent.put(value: Float) = putExtra(Float::class.java.name, value)

fun Intent.getLong() = getLongExtra(Long::class.java.name, 0L)
fun Intent.put(value: Long) = putExtra(Long::class.java.name, value)
