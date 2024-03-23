package com.example.bbsuestc.friendRequestActivity

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.recyclerViewContents.FriendRequestContent.TreatedRequestContentAdapter
import com.example.bbsuestc.recyclerViewContents.FriendRequestContent.TreatedRequestItem
import com.example.bbsuestc.recyclerViewContents.FriendRequestContent.UntreatedRequestContentAdapter
import com.example.bbsuestc.recyclerViewContents.FriendRequestContent.UntreatedRequestItem

class FriendRequestActivity : AppCompatActivity() {
    //返回按钮
    private lateinit var friendRequestBackIv:ImageView
    private lateinit var treatedRequestRv:RecyclerView
    private lateinit var treatedRequestList:ArrayList<TreatedRequestItem>
    private lateinit var untreatedRequestRv:RecyclerView
    private lateinit var untreatedRequestList:ArrayList<UntreatedRequestItem>
    private lateinit var requestViewModel:FriendRequestViewModel
    private lateinit var treatedAdapter:TreatedRequestContentAdapter
    private lateinit var untreatedAdapter:UntreatedRequestContentAdapter
    private lateinit var observer: Observer<ArrayList<UntreatedRequestItem>>

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
        untreatedRequestList= arrayListOf<UntreatedRequestItem>()
        treatedRequestList= arrayListOf<TreatedRequestItem>()
        //untreatedRequestList= requestViewModel.untreatedRequestList?.value!!
        untreatedAdapter= UntreatedRequestContentAdapter(requestViewModel.untreatedRequestList?.value!!,this,requestViewModel)
        treatedAdapter= TreatedRequestContentAdapter(treatedRequestList,this,requestViewModel)
        untreatedAdapter.initListener()
        treatedAdapter.initListener()
        //添加观察者
        requestViewModel.untreatedRequestList!!.observe(this,object : Observer<ArrayList<UntreatedRequestItem>>{
            override fun onChanged(value: ArrayList<UntreatedRequestItem>) {
                untreatedAdapter.updateData(requestViewModel.untreatedRequestList?.value!!)
                untreatedAdapter.notifyDataSetChanged()
            }
        })
        requestViewModel.treatedRequestList!!.observe(this){
            treatedAdapter.updateData(requestViewModel.treatedRequestList?.value!!)
            treatedAdapter.notifyDataSetChanged()
        }

        //设置未处理数据
        untreatedRequestRv.adapter=untreatedAdapter
        untreatedRequestRv.layoutManager=LinearLayoutManager(this)
        //设置已处理数据
        treatedRequestRv.adapter=treatedAdapter
        treatedRequestRv.layoutManager=LinearLayoutManager(this)

        treatedRequestRv.itemAnimator=null;

        untreatedRequestRv.itemAnimator=null;

    }

    fun getTreatedRequestRv(): RecyclerView {
        return treatedRequestRv
    }
}