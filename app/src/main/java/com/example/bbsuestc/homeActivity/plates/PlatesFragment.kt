package com.example.bbsuestc.homeActivity.plates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.bbsuestc.R

class PlatesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val platesViewModel =
            ViewModelProvider(this)[PlatesViewModel::class.java]

        val root: View = inflater.inflate(R.layout.fragment_plates,container,false)

        val plateGroupsRecyclerView = root.findViewById<RecyclerView>(R.id.plate_groups_RV)
        val layoutManager = LinearLayoutManager(this.context)
        val plateParentAdapter = PlateParentAdapter(platesViewModel.plateGroups)
        plateGroupsRecyclerView.apply {
            setLayoutManager(layoutManager)
            adapter = plateParentAdapter
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}