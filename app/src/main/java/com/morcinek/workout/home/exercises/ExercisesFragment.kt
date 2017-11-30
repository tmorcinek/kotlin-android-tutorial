package com.morcinek.workout.home.exercises

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.morcinek.kotlin.adapter.SectionRecyclerViewAdapter
import com.morcinek.workout.R
import com.morcinek.workout.common.firebase.data.DataProvider
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.core.data.exercises.ExerciseDataModel
import com.morcinek.workout.core.data.exercises.ExercisesProvider
import com.morcinek.workout.exercise.exerciseComponent
import com.morcinek.workout.home.exercises.adapter.ExerciseViewAdapter
import com.morcinek.workout.home.exercises.adapter.ExerciseViewModel
import kotlinx.android.synthetic.main.recycler_view.*
import org.jetbrains.anko.design.snackbar
import java.util.*
import javax.inject.Inject

class ExercisesFragment : BaseFragment(), DataProvider.Delegate<ExerciseDataModel> {

    override val layoutResourceId = R.layout.recycler_view

    @Inject lateinit var exercisesProvider: ExercisesProvider

    private val adapter: SectionRecyclerViewAdapter
        get() = recyclerView.adapter as SectionRecyclerViewAdapter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseComponent.inject(this)

        setupRecyclerView()
        exercisesProvider.delegate = this
        exercisesProvider.register()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        exercisesProvider.unregister()
    }

    override fun success(values: List<Pair<String, ExerciseDataModel>>) = adapter.setList(values.map {
        ExerciseViewModel(it.first, it.second.name, it.second.category, formatDate(it.second.date))
    })

    private fun formatDate(date: Calendar) = date.toString()

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = SectionRecyclerViewAdapter().apply {
            addSectionViewAdapter(ExerciseViewAdapter())
//            setItemViewClickListener { item, view ->
//            }
        }
    }

    override fun failed(errorMessage: String) {
        snackbar(view!!, errorMessage)
    }
}