package com.example.bbsuestc.homeActivity.home.homeContents.hot

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.homeActivity.home.homeContents.hot.headerServices.HeaderServicesAdapter

class HotFragment : Fragment() {

    private lateinit var viewModel: HotViewModel
    private lateinit var headerServices : RecyclerView
    private lateinit var postsContent : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_hot, container, false)

        headerServices = root.findViewById(R.id.hot_header_services_rv)
        postsContent = root.findViewById(R.id.hot_posts_content_rv)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        headerServices.layoutManager = layoutManager
        headerServices.adapter = HeaderServicesAdapter(context)

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HotViewModel::class.java)
    }

}