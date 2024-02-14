package com.example.bbsuestc.homeActivity.home.homeContents.digest

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
import com.example.bbsuestc.recyclerViewContents.postsContent.PostsItem

class DigestFragment : Fragment() {

    private lateinit var postsContent : RecyclerView
    private lateinit var viewModel: DigestViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_new_release, container, false)
        //data应该从ViewModel里获取
        postsContent = root.findViewById(R.id.digest_posts_rv)
        val data = arrayListOf<PostsItem>()
        for(i in 0..10){
            data.add(
                PostsItem("","这是精华发帖人ID","2022-12-22","这是精华帖子的标题",
                    "这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容",
                    "校园生活",114514,191)
            )
        }
        postsContent.layoutManager = LinearLayoutManager(activity)
        postsContent.adapter = PostsContentAdapter(data)
        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DigestViewModel::class.java)
    }

    companion object {
        fun newInstance() = DigestFragment()
    }

}