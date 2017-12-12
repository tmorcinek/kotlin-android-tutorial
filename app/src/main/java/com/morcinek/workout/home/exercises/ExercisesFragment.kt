package com.morcinek.workout.home.exercises

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.morcinek.kotlin.adapter.SectionRecyclerViewAdapter
import com.morcinek.workout.R
import com.morcinek.workout.common.di.component
import com.morcinek.workout.common.firebase.data.DataProvider
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.common.utils.dateFormat
import com.morcinek.workout.common.utils.formatWith
import com.morcinek.workout.common.utils.startActivityFun
import com.morcinek.workout.core.data.exercises.ExerciseDataModel
import com.morcinek.workout.core.data.exercises.ExercisesProvider
import com.morcinek.workout.core.data.putKeyExtra
import com.morcinek.workout.exercise.ExerciseActivity
import com.morcinek.workout.home.exercises.adapter.ExerciseViewAdapter
import com.morcinek.workout.home.exercises.adapter.ExerciseViewModel
import kotlinx.android.synthetic.main.recycler_view.*
import org.jetbrains.anko.design.snackbar
import java.util.*
import javax.inject.Inject

class ExercisesFragment : BaseFragment(), DataProvider.Delegate<ExerciseDataModel> {

    override val layoutResourceId = R.layout.recycler_view

    private val dateFormat by lazy { dateFormat() }

    @Inject lateinit var exercisesProvider: ExercisesProvider

    private val adapter: SectionRecyclerViewAdapter
        get() = recyclerView.adapter as SectionRecyclerViewAdapter

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        component.inject(this)

        setupRecyclerView()
        exercisesProvider.delegate = this
        exercisesProvider.register()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        exercisesProvider.unregister()
    }

    override fun success(values: List<Pair<String, ExerciseDataModel>>) = adapter.setList(values.map {
        ExerciseViewModel(it.first, it.second.name, it.second.category ?: "", formatDate(it.second.date))
    })

    private fun formatDate(date: Calendar) = date.formatWith(dateFormat)

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = SectionRecyclerViewAdapter().apply {
            addSectionViewAdapter(ExerciseViewAdapter())
            onSectionItemClickListener = object : SectionRecyclerViewAdapter.OnSectionItemClickListener {
                override fun onSectionItemClicked(itemView: View, view: View, item: Any, position: Int) {
                    activity.startActivityFun<ExerciseActivity> {
                        item as ExerciseViewModel
                        putKeyExtra(item.key)
                    }
                }
            }
        }
    }

    override fun failed(errorMessage: String) {
        snackbar(view!!, errorMessage)
    }
}