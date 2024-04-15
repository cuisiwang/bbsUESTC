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
import com.example.bbsuestc.recyclerViewContents.headerServices.HeaderServicesAdapter
import com.example.bbsuestc.recyclerViewContents.postsContent.PostsContentAdapter
import com.example.bbsuestc.recyclerViewContents.postsContent.PostsItem
import com.example.bbsuestc.testUtils.TestData
import com.example.bbsuestc.utils.fixHeight

class HotFragment : Fragment() {

    private lateinit var viewModel: HotViewModel
    private lateinit var headerServices : RecyclerView
    private lateinit var postsContent : RecyclerView
    private val dataList : ArrayList<PostsItem> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_hot, container, false)

        headerServices = root.findViewById(R.id.hot_header_services_rv)
        postsContent = root.findViewById(R.id.hot_posts_rv)
        postsContent.layoutManager = LinearLayoutManager(activity)
        postsContent.adapter = PostsContentAdapter(dataList)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        headerServices.layoutManager = layoutManager
        headerServices.adapter = HeaderServicesAdapter(context, TestData.homeHotHeaderData())

        //data应该从ViewModel里获取
//        postsContent.fixHeight()
        for (i in TestData.postData()){
            dataList.add(i)
        }
        postsContent.adapter!!.notifyItemRangeChanged(0,dataList.size-1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HotViewModel::class.java)
    }

    companion object {
        fun newInstance() = HotFragment()
    }

}