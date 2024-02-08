package com.example.bbsuestc.homeActivity.plates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.bbsuestc.R

class PlateChildAdapter(val plates: List<Plate>): Adapter<PlateChildAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_single_plate, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.plateNameTextView.text = plates[position].name
    }

    override fun getItemCount(): Int = plates.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val plateNameTextView: TextView

        init {
            plateNameTextView = view.findViewById(R.id.plate_name) as TextView
        }
    }
}