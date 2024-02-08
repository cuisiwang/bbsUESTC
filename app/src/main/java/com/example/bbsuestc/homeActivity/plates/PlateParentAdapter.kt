package com.example.bbsuestc.homeActivity.plates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.bbsuestc.R

class PlateParentAdapter(val plateGroups: List<PlateGroup>): Adapter<PlateParentAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_plate_group, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(parentHolder: ViewHolder, position: Int) {
        val plateGroup = plateGroups[position]
        parentHolder.groupTitleTextView.text = plateGroup.groupTitle

        val layoutManager = GridLayoutManager(parentHolder.platesRV.context,2)
        layoutManager.initialPrefetchItemCount =
            plateGroup.plates.size

        val plateChildAdapter = PlateChildAdapter(plateGroup.plates)
        parentHolder.platesRV.apply {
            setLayoutManager(layoutManager)
            adapter = plateChildAdapter
        }

    }

    override fun getItemCount(): Int = plateGroups.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val groupTitleTextView: TextView
        val platesRV: RecyclerView

        init {
            groupTitleTextView = view.findViewById(R.id.plate_group_title) as TextView
            platesRV = view.findViewById(R.id.plates_RV) as RecyclerView
        }


    }
}