package com.morcinek.workout.common.di

import android.app.Service
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.morcinek.workout.Application

val AppCompatActivity.component: ApplicationComponent
    get() = (application as Application).component

val Fragment.component: ApplicationComponent
    get() = (activity.application as Application).component

val Service.component: ApplicationComponent
    get() = (application as Application).component
