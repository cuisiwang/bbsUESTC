package com.example.bbsuestc.postsPertinentActivity.postsPertinentContents.reply

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bbsuestc.R

class PostsPertinentReplyFragment : Fragment() {

    companion object {
        fun newInstance() = PostsPertinentReplyFragment()
    }

    private lateinit var viewModel: PostsPertinentReplyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts_pertinent_reply, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PostsPertinentReplyViewModel::class.java)

    }

}