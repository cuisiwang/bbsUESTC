package com.example.bbsuestc.postsPertinentActivity.postsPertinentContents.commentary

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bbsuestc.R

class PostPertinentCommentaryFragment : Fragment() {

    companion object {
        fun newInstance() = PostPertinentCommentaryFragment()
    }

    private lateinit var viewModel: PostPertinentCommentaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_post_pertinent_commentary, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostPertinentCommentaryViewModel::class.java)

    }

}