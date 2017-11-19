package com.morcinek.workout.common.utils

import android.content.SharedPreferences

fun SharedPreferences.getInt(preferenceKey: String) = getInt(preferenceKey, 0)
fun SharedPreferences.getIntOrNull(preferenceKey: String) = if (contains(preferenceKey)) getInt(preferenceKey) else null

fun SharedPreferences.getLong(preferenceKey: String) = getLong(preferenceKey, 0)
fun SharedPreferences.getLongOrNull(preferenceKey: String) = if (contains(preferenceKey)) getLong(preferenceKey, 0) else null

fun SharedPreferences.getBoolean(preferenceKey: String) = getBoolean(preferenceKey, false)
fun SharedPreferences.getBooleanOrNull(preferenceKey: String) = if (contains(preferenceKey)) getBoolean(preferenceKey, false) else null

fun SharedPreferences.commitFunction(function: SharedPreferences.Editor.() -> SharedPreferences.Editor) = edit().function().commit()

fun SharedPreferences.applyFunction(function: SharedPreferences.Editor.() -> SharedPreferences.Editor) = edit().function().apply()

