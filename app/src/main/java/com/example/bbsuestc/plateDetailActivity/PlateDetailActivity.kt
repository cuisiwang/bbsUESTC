package com.example.bbsuestc.plateDetailActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.bbsuestc.R
import com.example.bbsuestc.newPostActivity.NewPostActivity
import com.example.bbsuestc.recyclerViewContents.PinnedPostsAdapter
import com.example.bbsuestc.recyclerViewContents.headerServices.HeaderServicesAdapter
import com.example.bbsuestc.testUtils.TestData
import com.example.bbsuestc.utils.adjustScrollSensitivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

//intent key所用常量
//TODO 常量统一放在某个位置
const val EXTRA_PLATE_ID = "plate_id"
const val EXTRA_PLATE_NAME = "plate_name"

//对应 模块详情 页面
class PlateDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: PlateDetailViewModel

    private lateinit var backButton: ImageButton
    private lateinit var plateTitleTextView: TextView
    private lateinit var moderatorTextView: TextView
    private lateinit var postsTodayStatisticsTextView: TextView
    private lateinit var postsStatisticsTextView: TextView
    private lateinit var headerServiceRV: RecyclerView
//TODO:写pinned adapter
    private lateinit var pinnedServiceRV: RecyclerView
    private lateinit var contentsTL: TabLayout
    private lateinit var contentVP: ViewPager2
//TODO:发帖按钮
//    修改发帖activity, 使能传入板块信息
  private lateinit var newPostFAB : FloatingActionButton


    private var plateId = 0
    private var plateTitle = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plate_detail)

        viewModel = ViewModelProvider(this)[PlateDetailViewModel::class.java]

        backButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }

        moderatorTextView = findViewById(R.id.moderator_TV)
        postsTodayStatisticsTextView = findViewById(R.id.posts_today_statistics_TV)
        postsStatisticsTextView = findViewById(R.id.posts_statistics_TV)
        bindingInfo(TestData.PlateInfo.mod,
            TestData.PlateInfo.postsToday,
            TestData.PlateInfo.posts)

        plateTitleTextView = findViewById(R.id.plate_title)
        plateId = intent.getIntExtra(EXTRA_PLATE_ID, 0)
        plateTitle = intent.getStringExtra(EXTRA_PLATE_NAME).toString()
        plateTitleTextView.text = plateTitle

        headerServiceRV = findViewById(R.id.header_services_rv)
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        headerServiceRV.layoutManager = layoutManager
        headerServiceRV.adapter = HeaderServicesAdapter(this, TestData.plateHotHeaderData())

        pinnedServiceRV = findViewById(R.id.pinned_posts_RV)
        pinnedServiceRV.adapter = PinnedPostsAdapter(viewModel.pinnedPosts)
        pinnedServiceRV.layoutManager = LinearLayoutManager(this)

        contentsTL = findViewById(R.id.tab_contents_tl)
        contentVP = findViewById(R.id.plate_posts_VP)

        contentVP.adapter = PlateContentVPAdapter(this)
        contentVP.adjustScrollSensitivity(contentVP,2)
        contentVP.offscreenPageLimit =  2

        val titles = arrayOf("热门", "最新回复", "最新发表", "精华")
        TabLayoutMediator(contentsTL, contentVP) { tab, position ->
            tab.setText(titles[position])
        }.attach()


        newPostFAB = findViewById(R.id.new_post_fab)
        newPostFAB.setOnClickListener{
            val intent = Intent(this, NewPostActivity::class.java)
            startActivity(intent)
        }
    }

    private fun bindingInfo(mod: String, postsToday: Int, posts: Int) {
        moderatorTextView.text = mod
        postsTodayStatisticsTextView.text = postsToday.toString()
        postsStatisticsTextView.text = posts.toString()
    }


}