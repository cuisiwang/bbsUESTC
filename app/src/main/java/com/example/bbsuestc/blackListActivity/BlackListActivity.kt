package com.example.bbsuestc.blackListActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.recyclerViewContents.blackListContent.BlackListContentAdapter

class BlacklistActivity : AppCompatActivity() {
    //返回图标
    private lateinit var blacklistBackIcon: ImageView
    private lateinit var blacklistRecyclerView: RecyclerView
    private lateinit var blacklistRvAdapter: BlackListContentAdapter
    private lateinit var blacklistViewModel: BlackListViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blacklist)

        blacklistBackIcon = findViewById(R.id.blacklist_back_iv)
        blacklistRecyclerView = findViewById(R.id.blacklist_rv)
        blacklistViewModel = ViewModelProvider(this)[BlackListViewModel::class.java]
        blacklistBackIcon.setOnClickListener {
            finish()
        }
        initData()
    }

    private fun initData() {
        blacklistRvAdapter = BlackListContentAdapter(
            blacklistViewModel.blackList.value,
            this,
            blacklistViewModel
        )
        //添加观察者
        blacklistViewModel.blackList.observe(this) {
            blacklistRvAdapter.notifyDataSetChanged()
        }
        blacklistRecyclerView.adapter = blacklistRvAdapter
        blacklistRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun getBlacklistRV(): RecyclerView {
        return blacklistRecyclerView
    }
}
