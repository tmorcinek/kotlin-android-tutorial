package com.morcinek.workout.home.exercises.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.morcinek.kotlin.adapter.SectionRecyclerViewAdapter
import com.morcinek.workout.R
import kotlinx.android.synthetic.main.exercises_item.view.*

/**
 * Copyright 2017 Tomasz Morcinek. All rights reserved.
 */
class ExerciseViewAdapter : SectionRecyclerViewAdapter.SectionViewAdapter<ExerciseViewModel, ExerciseViewAdapter.ViewHolder> {

    override fun onBindViewHolder(holder: ViewHolder, item: ExerciseViewModel, position: Int) {
        holder.name.text = item.name
        holder.category.text = "Category"
        holder.date.text = item.date
        holder.time.text = item.time
    }

    override fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup, viewType: Int)
            = ViewHolder(layoutInflater.inflate(R.layout.exercises_item, parent, false))

    override fun clickableViews(holder: ViewHolder) = listOf(holder.itemView)

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView
            get() = itemView.name
        val category: TextView
            get() = itemView.category
        val date: TextView
            get() = itemView.date
        val time: TextView
            get() = itemView.time
    }
}

class ExerciseViewModel(val key: String, val name: String, val category: String, val date: String, val time: String)