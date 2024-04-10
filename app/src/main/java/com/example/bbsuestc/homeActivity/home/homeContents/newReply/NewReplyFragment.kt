package com.example.bbsuestc.homeActivity.home.homeContents.newReply

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.recyclerViewContents.postsContent.PostsContentAdapter
import com.example.bbsuestc.recyclerViewContents.postsContent.PostsItem
import com.example.bbsuestc.testUtils.TestData
import com.example.bbsuestc.utils.fixHeight

class NewReplyFragment : Fragment() {
    private lateinit var postsContent: RecyclerView
    private lateinit var viewModel: NewReplyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_new_reply, container, false)

        postsContent = root.findViewById(R.id.new_reply_posts_rv)
        Log.e("AAAA", "onCreateView: 1111" )

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //data应该从ViewModel里获取
        postsContent.fixHeight()
        val data = TestData.postData()
        postsContent.layoutManager = LinearLayoutManager(activity)
        postsContent.adapter = PostsContentAdapter(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewReplyViewModel::class.java)
    }


    companion object {
        fun newInstance() = NewReplyFragment()
    }

}