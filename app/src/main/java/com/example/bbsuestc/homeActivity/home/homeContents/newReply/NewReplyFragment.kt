package com.example.bbsuestc.homeActivity.home.homeContents.newReply

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bbsuestc.R

class NewReplyFragment : Fragment() {

    companion object {
        fun newInstance() = NewReplyFragment()
    }

    private lateinit var viewModel: NewReplyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_reply, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewReplyViewModel::class.java)
        // TODO: Use the ViewModel
    }

}