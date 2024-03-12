package com.example.bbsuestc.postsPertinentActivity.postsPertinentContents.at

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bbsuestc.R

class PostPertinentAtFragment : Fragment() {

    companion object {
        fun newInstance() = PostPertinentAtFragment()
    }

    private lateinit var viewModel: PostPertinentAtViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_pertinent_at, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostPertinentAtViewModel::class.java)

    }

}