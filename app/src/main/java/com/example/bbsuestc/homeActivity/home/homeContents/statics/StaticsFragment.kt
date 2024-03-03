package com.example.bbsuestc.homeActivity.home.homeContents.statics

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bbsuestc.R

class StaticsFragment : Fragment() {

    companion object {
        fun newInstance() = StaticsFragment()
    }

    private lateinit var viewModel: StaticsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statics, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(StaticsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}