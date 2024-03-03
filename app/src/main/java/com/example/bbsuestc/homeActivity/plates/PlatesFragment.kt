package com.example.bbsuestc.homeActivity.plates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bbsuestc.R

class PlatesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val platesViewModel =
            ViewModelProvider(this).get(PlatesViewModel::class.java)

        val root: View = inflater.inflate(R.layout.fragment_plates,container,false)

        val textView: TextView = root.findViewById(R.id.text_plates)
        platesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}