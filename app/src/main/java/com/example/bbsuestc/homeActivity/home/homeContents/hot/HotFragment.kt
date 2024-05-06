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
import com.example.bbsuestc.utils.APIStatics
import com.example.bbsuestc.utils.fixHeight
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HotFragment : Fragment() {


    private lateinit var headerServices: RecyclerView
    private lateinit var postsContent: RecyclerView
    private val dataList: ArrayList<PostsItem> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_hot, container, false)

        headerServices = root.findViewById(R.id.hot_header_services_rv)
        postsContent = root.findViewById(R.id.hot_posts_rv)

        val viewModel: HotViewModel by lazy {
            ViewModelProvider(this)[HotViewModel::class.java]
        }
        viewModel.getHotPosts(dataList)
        postsContent.adapter!!.notifyItemRangeChanged(0, dataList.size - 1)

        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        headerServices.layoutManager = layoutManager
        headerServices.adapter = HeaderServicesAdapter(context, TestData.homeHotHeaderData())
        postsContent.layoutManager = LinearLayoutManager(context)
        postsContent.adapter = PostsContentAdapter(dataList)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //data应该从ViewModel里获取
        postsContent.fixHeight()
        val dialog = layoutInflater.inflate(R.layout.progress_indicator_full_screen, null)
        dialog.visibility = View.VISIBLE
        activity?.addContentView(
            dialog,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        dialog.postDelayed({
            dialog.visibility = View.GONE
            for (i in TestData.postData()) {
                dataList.add(i)
            }

        }, 2100)
    }

    override fun onResume() {
        super.onResume()
        if (APIStatics.flag) {
            postsContent.postDelayed({
                val now = LocalDateTime.now()
                val fm = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                dataList.add(
                    0, PostsItem(
                        "1",
                        "崔思惘",
                        now.format(fm),
                        "求工作推荐！",
                        "大家好！楼主是24届的毕业生，现在在找工作，求推荐！" +
                                "\n" + "[图片]",
                        "前程似锦",
                        1,
                        0
                    )
                )
                postsContent.adapter!!.notifyItemInserted(0)
                postsContent.layoutManager!!.scrollToPosition(0)
                APIStatics.flag = false
            }, 912)
        }
    }

    companion object {
        fun newInstance() = HotFragment()
    }

}