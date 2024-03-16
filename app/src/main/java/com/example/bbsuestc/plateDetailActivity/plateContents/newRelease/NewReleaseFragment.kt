package com.example.bbsuestc.plateDetailActivity.plateContents.newRelease

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.plateDetailActivity.plateContents.hot.HotViewModel
import com.example.bbsuestc.recyclerViewContents.postsContent.PostsContentAdapter

class NewReleaseFragment : Fragment() {

    companion object {
        fun newInstance() = NewReleaseFragment()
    }

    private lateinit var viewModel: NewReleaseViewModel
    private lateinit var postsContent : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_new_release_plate_detail, container, false)
        postsContent = root.findViewById(R.id.new_release_posts_rv)
        postsContent.apply {
            layoutManager = LinearLayoutManager(context) // TODO 如遇到bug,context改为activity
            adapter = PostsContentAdapter(viewModel.data)
        }


        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewReleaseViewModel::class.java)
    }

}