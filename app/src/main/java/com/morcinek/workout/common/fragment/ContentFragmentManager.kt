package com.morcinek.workout.common.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.morcinek.workout.R

class ContentFragmentManager(private val activity: FragmentActivity, private val contentLayoutId: Int = R.id.contentFrame) {

    private val fragmentManager by lazy { activity.supportFragmentManager }

    val currentFragment: Fragment?
        get() = fragmentManager.fragments.firstOrNull { it?.isVisible ?: false }

    fun replaceFragment(fragment: Fragment, tag: String = fragment.javaClass.name, addToBackStack: Boolean = false) {
        if (currentFragment?.javaClass != fragment.javaClass) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(contentLayoutId, fragment, tag)
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(tag)
            }
            fragmentTransaction.commit()
        }
    }
}