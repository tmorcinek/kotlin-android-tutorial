package com.morcinek.workout.common.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

abstract class BaseFragment : Fragment() {

    protected abstract val layoutResourceId: Int

    open protected val menuResourceId: Int? = null

    private val hasMenu by lazy { menuResourceId != null }

    final override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?)
            = inflater?.inflate(layoutResourceId, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(hasMenu)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        if (hasMenu) {
            inflater?.inflate(menuResourceId!!, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }
}