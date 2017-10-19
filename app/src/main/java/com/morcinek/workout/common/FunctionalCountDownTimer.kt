package com.morcinek.workout.common

import android.os.CountDownTimer

class FunctionalCountDownTimer(millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

    private var onFinishFunction: (() -> Unit)? = null
    private var onTickFunction: ((Long) -> Unit)? = null

    fun onFinish(onFinishFunction: () -> Unit) {
        this.onFinishFunction = onFinishFunction
    }

    fun onTick(onTickFunction: (Long) -> Unit) {
        this.onTickFunction = onTickFunction
    }

    override fun onFinish() {
        onFinishFunction?.invoke()
    }

    override fun onTick(millisUntilFinished: Long) {
        onTickFunction?.invoke(millisUntilFinished)
    }
}