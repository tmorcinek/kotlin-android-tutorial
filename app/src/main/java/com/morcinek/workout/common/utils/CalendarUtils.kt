package com.morcinek.workout.common.utils

import com.morcinek.workout.BuildConfig
import java.text.SimpleDateFormat
import java.util.*

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */

fun Calendar.formatWith(dateFormat: SimpleDateFormat) = dateFormat.format(time)

fun dateFormat() = SimpleDateFormat(BuildConfig.DATE_FORMAT)
