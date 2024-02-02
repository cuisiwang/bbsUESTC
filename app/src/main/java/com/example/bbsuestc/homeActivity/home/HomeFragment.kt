package com.example.bbsuestc.homeActivity.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bbsuestc.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        val root: View = inflater.inflate(R.layout.fragment_home,container,false)

        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it  //这里的.text指的是TextView控件的text属性，可以对应的修改textSize一类的属性
            textView.textSize=it.length/1F
        }

        val tb : androidx.appcompat.widget.Toolbar = root.findViewById(R.id.home_toolbar)
        (activity as AppCompatActivity).setSupportActionBar(tb)

        textView.setOnClickListener { homeViewModel.changeText() }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}