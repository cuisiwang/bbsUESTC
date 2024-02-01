package com.example.bbsuestc.homeActivity.my

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.bbsuestc.R


class MyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_my, container, false)

        val myViewModel : MyViewModel = ViewModelProvider(this)[MyViewModel::class.java]
        val textView: TextView = root.findViewById(R.id.my_text)
        myViewModel.text.observe(viewLifecycleOwner){
            textView.text=it
        }

        return root
    }
}