package com.example.bbsuestc.postsRelativeActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.recyclerViewContents.postsRelativeContent.PostsRelativeContentAdapter
import com.example.bbsuestc.recyclerViewContents.postsRelativeContent.PostsRelativeItem
import com.example.bbsuestc.utils.fixHeight
import com.google.android.material.tabs.TabLayout

class PostsRelativeActivity : AppCompatActivity() {
    private lateinit var backIcon: ImageView
    private lateinit var viewModel: PostsRelativeViewModel
    private lateinit var tabItems:ArrayList<PostsRelativeTabItem>
    private lateinit var tabLayout: TabLayout
    private lateinit var postsRelativeRv:RecyclerView
    private lateinit var postsRelativeRvAdapter: PostsRelativeContentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts_relative)

        viewModel= ViewModelProvider(this)[PostsRelativeViewModel::class.java]
        tabItems=viewModel.tabItems
        tabLayout=findViewById(R.id.posts_relative_contents_tl)
        backIcon=findViewById(R.id.posts_relative_back_iv)
        postsRelativeRv=findViewById(R.id.posts_relative_rv)

        backIcon.setOnClickListener{
            finish()
        }
        initTab()
        initData()
        //TODO:设置tab的监听事件，切换相关显示，目前只有reply
    }

    private fun initData() {
        postsRelativeRvAdapter= PostsRelativeContentAdapter(viewModel.postsRelativeList.value!!,this)
        postsRelativeRv.adapter=postsRelativeRvAdapter
        postsRelativeRv.layoutManager=LinearLayoutManager(this)
        postsRelativeRv.fixHeight()
    }
    //初始化tab
    private fun initTab() {
        for(i in 0..4){
            val tab1=tabLayout.newTab().setCustomView(getTabView(i))
            val tabview=tab1.customView
            //设置tab大小,避免显示不全
            if(tabview!=null){
                tabview.layoutParams=LinearLayout.LayoutParams(250,ViewGroup.LayoutParams.WRAP_CONTENT)
            }
            tabLayout.addTab(tab1)
        }
    }
    //渲染对应位置的tab
    private fun getTabView(position: Int): View {
        val view: View = LayoutInflater.from(this).inflate(R.layout.posts_relative_tl_view,null)
        val textTitle : TextView =view.findViewById(R.id.posts_tab_note)
        val frameLayout: FrameLayout =view.findViewById(R.id.posts_relative_tl_fl)
        val cnt: TextView =view.findViewById(R.id.posts_relative_tl_cnt)
        textTitle.setText(tabItems[position].title)
        if(tabItems[position].haveNotice)
        {frameLayout.visibility= View.VISIBLE}
        else
        {frameLayout.visibility= View.GONE}
        cnt.setText(tabItems[position].noticeCnt.toString())
        return view
    }
}