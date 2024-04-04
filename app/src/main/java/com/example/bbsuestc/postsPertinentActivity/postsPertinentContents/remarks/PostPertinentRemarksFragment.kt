package com.example.bbsuestc.postsPertinentActivity.postsPertinentContents.remarks

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bbsuestc.R

class PostPertinentRemarksFragment : Fragment() {

    companion object {
        fun newInstance() = PostPertinentRemarksFragment()
    }

    private lateinit var viewModel: PostPertinentRemarksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_pertinent_remarks, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostPertinentRemarksViewModel::class.java)

    }

}