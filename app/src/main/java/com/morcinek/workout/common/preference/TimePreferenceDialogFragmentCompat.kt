package com.morcinek.workout.common.preference

import android.support.v7.preference.PreferenceDialogFragmentCompat
import android.view.View
import android.widget.NumberPicker
import com.morcinek.workout.R

class TimePreferenceDialogFragmentCompat : PreferenceDialogFragmentCompat() {

    companion object {
        const val ARG_KEY = "key"
        const val ARG_MIN = "min"
        const val ARG_MAX = "max"
        const val ARG_INCREMENT = "increment"
    }

    private lateinit var mTimePicker: NumberPicker

    private val max by lazy { arguments[ARG_MAX] as Int }
    private val min by lazy { arguments[ARG_MIN] as Int }
    private val increment by lazy { arguments[ARG_INCREMENT] as Int }

    private val timePreference: TimePreference
        get() = preference as TimePreference

    override fun onBindDialogView(view: View?) {
        super.onBindDialogView(view)

        mTimePicker = view!!.findViewById(R.id.edit)

        val minValue = 0
        val maxValue = (max - min) / increment

        mTimePicker.minValue = minValue
        mTimePicker.maxValue = maxValue
        mTimePicker.displayedValues = (minValue..maxValue).map { min + it * increment }
                .map { "$it" }
                .toTypedArray()

        mTimePicker.value = getPickerValue(timePreference.time)
    }

    private fun getReturnValue(localValue: Int) = localValue * increment + min

    private fun getPickerValue(returnValue: Int) = (returnValue - min) / increment

    override fun onDialogClosed(positiveResult: Boolean) {
        if (positiveResult) {
            val value = mTimePicker.value
            if (preference.callChangeListener(value)) {
                timePreference.time = getReturnValue(value)
            }
        }
    }
}