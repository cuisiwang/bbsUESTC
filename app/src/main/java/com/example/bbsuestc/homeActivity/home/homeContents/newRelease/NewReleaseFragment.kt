package com.example.bbsuestc.homeActivity.home.homeContents.newRelease

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bbsuestc.R

class NewReleaseFragment : Fragment() {

    companion object {
        fun newInstance() = NewReleaseFragment()
    }

    private lateinit var viewModel: NewReleaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_release, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[NewReleaseViewModel::class.java]
    }

}