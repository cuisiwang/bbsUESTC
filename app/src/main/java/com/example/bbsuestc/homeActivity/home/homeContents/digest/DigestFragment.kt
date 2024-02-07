package com.example.bbsuestc.homeActivity.home.homeContents.digest

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bbsuestc.R

class DigestFragment : Fragment() {

    companion object {
        fun newInstance() = DigestFragment()
    }

    private lateinit var viewModel: DigestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_digest, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DigestViewModel::class.java)
        // TODO: Use the ViewModel
    }

}