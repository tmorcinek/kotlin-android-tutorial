package com.morcinek.workout.settings

import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.preference.Preference
import android.support.v7.preference.PreferenceFragmentCompat
import com.morcinek.workout.R
import com.morcinek.workout.common.preference.TimePreference
import com.morcinek.workout.common.preference.TimePreferenceDialogFragmentCompat
import org.jetbrains.anko.bundleOf


class SettingsFragment : PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings)

        updateGeneralBreakTime(preferenceManager.sharedPreferences)
        updateNotificationVibrationTime(preferenceManager.sharedPreferences)
    }

    override fun onDisplayPreferenceDialog(preference: Preference?) {
        if (preference is TimePreference) {
            showDialogFragment(TimePreferenceDialogFragmentCompat().apply {
                arguments = bundleOf(
                        TimePreferenceDialogFragmentCompat.ARG_KEY to preference.getKey(),
                        TimePreferenceDialogFragmentCompat.ARG_MIN to 10,
                        TimePreferenceDialogFragmentCompat.ARG_MAX to 200,
                        TimePreferenceDialogFragmentCompat.ARG_INCREMENT to 5
                )
            })
        } else {
            super.onDisplayPreferenceDialog(preference)
        }
    }

    private fun showDialogFragment(dialogFragment: DialogFragment) {
        dialogFragment.setTargetFragment(this, 0)
        dialogFragment.show(this.fragmentManager, "android.support.v7.preference" + ".PreferenceFragment.DIALOG")
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key){
            "notificationVibrationEnabled" -> updateNotificationVibrationTime(sharedPreferences)
            "generalBreakTime" -> updateGeneralBreakTime(sharedPreferences)
        }
    }

    private fun updateGeneralBreakTime(sharedPreferences: SharedPreferences?) {
        preferenceScreen.findPreference("generalBreakTime").summary = getString(R.string.breakTimeSummary, sharedPreferences!!.getInt("generalBreakTime", 0))
    }

    private fun updateNotificationVibrationTime(sharedPreferences: SharedPreferences?) {
        preferenceScreen.findPreference("notificationVibrationTime").isEnabled = sharedPreferences!!.getBoolean("notificationVibrationEnabled", false)
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }
}