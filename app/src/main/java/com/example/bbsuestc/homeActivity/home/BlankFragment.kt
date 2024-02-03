package com.example.bbsuestc.homeActivity.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bbsuestc.R

class BlankFragment : Fragment() {

//    做了一个空的fragment作为bottomNavigation的跳板，不然默认初始加载的fragment拿不到，不好实现复用逻辑
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false)
    }

}