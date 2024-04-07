package com.example.bbsuestc.plateDetailActivity.plateContents.newReply

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.recyclerViewContents.postsContent.PostsContentAdapter
import com.example.bbsuestc.utils.fixHeight

class NewReplyFragment : Fragment() {

    companion object {
        fun newInstance() = NewReplyFragment()
    }

    private lateinit var viewModel: NewReplyViewModel
    private lateinit var postsContent : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_new_reply_plate_detail, container, false)
        postsContent = root.findViewById(R.id.new_reply_posts_rv)
        postsContent.apply {
            fixHeight()
            layoutManager = LinearLayoutManager(context) // TODO 如遇到bug,context改为activity
            adapter = PostsContentAdapter(viewModel.data)
        }

        return root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewReplyViewModel::class.java)
    }


}