package com.morcinek.workout.common.di

import android.support.v7.app.AppCompatActivity
import com.morcinek.workout.Application

val AppCompatActivity.component: ApplicationComponent
    get() = (application as Application).component
