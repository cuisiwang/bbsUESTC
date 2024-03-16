package com.example.bbsuestc.plateDetailActivity.plateContents.hot

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.recyclerViewContents.headerServices.HeaderServicesAdapter
import com.example.bbsuestc.recyclerViewContents.postsContent.PostsContentAdapter

class HotFragment : Fragment() {

    companion object {
        fun newInstance() = HotFragment()
    }

    private lateinit var viewModel: HotViewModel
    private lateinit var postsContent : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_hot_plate_detail, container, false)
        postsContent = root.findViewById(R.id.hot_posts_rv)
        postsContent.apply {
            layoutManager = LinearLayoutManager(context) // TODO 如遇到bug,context改为activity
            adapter = PostsContentAdapter(viewModel.data)
        }


        return root


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HotViewModel::class.java)
    }

}