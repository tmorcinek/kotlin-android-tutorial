package com.morcinek.workout.exercise.fragments

import android.os.Bundle
import android.view.View
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.morcinek.workout.R
import com.morcinek.workout.common.fragment.BaseFragment
import com.morcinek.workout.core.data.exercises.ExerciseDataModel
import com.morcinek.workout.core.data.exercises.ExerciseManager
import com.morcinek.workout.core.data.getKeyExtra
import com.morcinek.workout.exercise.ExerciseDataManager
import com.morcinek.workout.exercise.exerciseComponent
import org.jetbrains.anko.design.snackbar
import javax.inject.Inject

class LoadingFragment : BaseFragment(), ValueEventListener {

    override val layoutResourceId = R.layout.exercise_loading

    @Inject lateinit var exerciseDataManager: ExerciseDataManager
    @Inject lateinit var exerciseManager: ExerciseManager

    val key by lazy { activity.intent.getKeyExtra() }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        exerciseComponent.inject(this)

        if (key == null) {
            val exerciseDataModel = ExerciseDataModel("Exercise 1")
            exerciseManager.update(exerciseDataModel).addOnCompleteListener {
                if (it.isSuccessful) {
                    exerciseDataManager.showNew()
                    exerciseDataManager.exerciseDataModel = exerciseDataModel
                } else {
                    snackbar(view!!, it.exception?.localizedMessage ?: "")
                }
            }
        } else {
            exerciseManager.key = key
            exerciseManager.get().addListenerForSingleValueEvent(this)
        }
    }

    override fun onCancelled(p0: DatabaseError?) {
        snackbar(view!!, p0?.message ?: "")
    }

    override fun onDataChange(p0: DataSnapshot?) {
        exerciseDataManager.exerciseDataModel = p0!!.getValue(ExerciseDataModel::class.java)!!
        exerciseDataManager.showSeries()
    }
}