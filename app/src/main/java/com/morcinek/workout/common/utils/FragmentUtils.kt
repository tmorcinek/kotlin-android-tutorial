package com.morcinek.workout.common.utils

import android.support.v7.app.AppCompatActivity

fun android.support.v4.app.Fragment.setTitle(resourceId: Int) {
    (activity as AppCompatActivity).supportActionBar!!.setTitle(resourceId)
}
