package com.example.bbsuestc.systemMessageActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.recyclerViewContents.messageSystemContent.MessageSystemContentAdapter
import com.example.bbsuestc.recyclerViewContents.messageSystemContent.MessageSystemItem
import com.example.bbsuestc.systemMessageActivity.SystemMessageViewModel

class SystemMessageActivity : AppCompatActivity() {
    private lateinit var backIcon:ImageView
    private lateinit var messageSystemRv:RecyclerView
    private lateinit var adapter:MessageSystemContentAdapter
    private lateinit var systemList:ArrayList<MessageSystemItem>
    private val systemMessageViewModel: SystemMessageViewModel by lazy {
        ViewModelProvider(this)[SystemMessageViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_message)

        backIcon=findViewById(R.id.message_system_back_iv)
        messageSystemRv=findViewById(R.id.message_system_rv)
        backIcon.setOnClickListener{
            finish()
        }
        initData()
    }
    private fun initData() {
        systemList= arrayListOf<MessageSystemItem>()
        systemList= systemMessageViewModel.systemMessageList.value!!
        adapter= MessageSystemContentAdapter(systemList,this,systemMessageViewModel)
        messageSystemRv.adapter=adapter
        messageSystemRv.layoutManager=LinearLayoutManager(this)
        systemMessageViewModel.systemMessageList.observe(this){
            adapter.notifyDataSetChanged()
        }

    }
}