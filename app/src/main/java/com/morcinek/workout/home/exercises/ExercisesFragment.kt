package com.morcinek.workout.home.exercises

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.morcinek.kotlin.adapter.SectionRecyclerViewAdapter
import com.morcinek.workout.R
import com.morcinek.workout.common.di.component
import com.morcinek.workout.common.firebase.data.InteractorDelegate
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.common.utils.startActivityFun
import com.morcinek.workout.core.data.putKeyExtra
import com.morcinek.workout.exercise.ExerciseActivity
import com.morcinek.workout.home.exercises.adapter.ExerciseViewAdapter
import com.morcinek.workout.home.exercises.adapter.ExerciseViewModel
import kotlinx.android.synthetic.main.recycler_view.*
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject

class ExercisesFragment : BaseFragment(), InteractorDelegate<List<ExerciseViewModel>> {

    override val layoutResourceId = R.layout.recycler_view

    @Inject lateinit var exercisesInteractor: ExercisesInteractor

    private val adapter: SectionRecyclerViewAdapter
        get() = recyclerView.adapter as SectionRecyclerViewAdapter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)

        setupRecyclerView()
        exercisesInteractor.register(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        exercisesInteractor.unregister()
    }

    override fun success(values: List<ExerciseViewModel>) = adapter.setList(values)

    override fun failed(errorMessage: String) {
        snackbar(view!!, errorMessage)
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = SectionRecyclerViewAdapter().apply {
            addSectionViewAdapter(ExerciseViewAdapter())
            setItemClickListener {
                activity.startActivityFun<ExerciseActivity> {
                    it as ExerciseViewModel
                    putKeyExtra(it.key)
                }
            }
        }
    }
}