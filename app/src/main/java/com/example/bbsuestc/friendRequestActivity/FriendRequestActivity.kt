package com.example.bbsuestc.friendRequestActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.recyclerViewContents.friendRequestContent.TreatedRequestContentAdapter
import com.example.bbsuestc.recyclerViewContents.friendRequestContent.TreatedRequestItem
import com.example.bbsuestc.recyclerViewContents.friendRequestContent.UntreatedRequestContentAdapter
import com.example.bbsuestc.recyclerViewContents.friendRequestContent.UntreatedRequestItem
import com.example.bbsuestc.utils.fixHeight

class FriendRequestActivity : AppCompatActivity() {
    //返回按钮
    private lateinit var friendRequestBackIv:ImageView
    private lateinit var treatedRequestRv:RecyclerView
    private lateinit var untreatedRequestRv:RecyclerView
    private lateinit var requestViewModel:FriendRequestViewModel
    private lateinit var treatedAdapter:TreatedRequestContentAdapter
    private lateinit var untreatedAdapter:UntreatedRequestContentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_request)

        friendRequestBackIv=findViewById(R.id.friend_request_back_iv)
        treatedRequestRv=findViewById(R.id.friend_request_treated_rv)
        untreatedRequestRv=findViewById(R.id.friend_request_untreated_rv)
        requestViewModel= ViewModelProvider(this)[FriendRequestViewModel::class.java]

        //返回按钮
        friendRequestBackIv.setOnClickListener{
            finish()
        }
        initData();
    }
    public fun getUntreatedRv(): RecyclerView {
        return untreatedRequestRv
    }

    private fun initData() {
        //初始化适配器和数据项
        untreatedAdapter= UntreatedRequestContentAdapter(requestViewModel.untreatedRequestList.value!!,this,requestViewModel)
        treatedAdapter= TreatedRequestContentAdapter(requestViewModel.treatedRequestList.value!!,this,requestViewModel)

        //添加观察者
        requestViewModel.untreatedRequestList.observe(this) {
            untreatedAdapter.notifyDataSetChanged()
        }
        requestViewModel.treatedRequestList.observe(this){
            treatedAdapter.notifyDataSetChanged()
        }

        //设置未处理数据
        untreatedRequestRv.adapter=untreatedAdapter
        untreatedRequestRv.layoutManager=LinearLayoutManager(this)
        //设置已处理数据
        treatedRequestRv.adapter=treatedAdapter
        treatedRequestRv.layoutManager=LinearLayoutManager(this)
    }

    fun getTreatedRequestRv(): RecyclerView {
        return treatedRequestRv
    }
}