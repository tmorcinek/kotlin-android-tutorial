package com.morcinek.workout.common.preference

import android.content.Context
import android.content.res.TypedArray
import android.support.v7.preference.DialogPreference
import android.util.AttributeSet
import com.morcinek.workout.R

class TimePreference(context: Context?, attrs: AttributeSet?) : DialogPreference(context, attrs) {

    var time: Int = 0
        set(value) {
            field = value
            persistInt(time)
        }

    override fun getDialogLayoutResource() = R.layout.pref_dialog_time

    override fun onGetDefaultValue(a: TypedArray?, index: Int): Any? = a?.getInt(index, 0)

    override fun onSetInitialValue(restorePersistedValue: Boolean, defaultValue: Any?) {
        time = if (restorePersistedValue) getPersistedInt(time) else defaultValue as Int
    }
}