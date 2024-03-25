package com.example.bbsuestc.blacklistActivity

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.recyclerViewContents.blacklistContent.BlacklistContentAdapter
import com.example.bbsuestc.recyclerViewContents.blacklistContent.BlacklistItem

class BlacklistActivity : AppCompatActivity() {
    //返回图标
    private lateinit var blacklistBackIcon:ImageView
    private lateinit var blacklistRecyclerView:RecyclerView
    private lateinit var blacklistRvAdapter: BlacklistContentAdapter
    private lateinit var blacklistViewModel: BlacklistViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blacklist)
        blacklistBackIcon=findViewById(R.id.blacklist_back_iv)
        blacklistRecyclerView=findViewById(R.id.blacklist_rv)
        blacklistViewModel= ViewModelProvider(this)[BlacklistViewModel::class.java]
        blacklistBackIcon.setOnClickListener{
            finish()
        }
        initData()
    }

    private fun initData() {
        blacklistRvAdapter = BlacklistContentAdapter(
            blacklistViewModel.getBlackList().value!!,
            this,
            blacklistViewModel
        )
        //添加观察者
        blacklistViewModel.getBlackList().observe(this) {
            blacklistRvAdapter.notifyDataSetChanged()
        }
        blacklistRecyclerView.adapter = blacklistRvAdapter
        blacklistRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    fun getBlacklistRV():RecyclerView{
        return blacklistRecyclerView
    }
}
