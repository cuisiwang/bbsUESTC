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

class SystemMessageActivity : AppCompatActivity() {
    private lateinit var backIcon:ImageView
    private lateinit var messageSystemRv:RecyclerView
    private lateinit var adapter:MessageSystemContentAdapter
    private lateinit var systemList:ArrayList<MessageSystemItem>
    private lateinit var systemMessageViewModel: SystemMessageViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_system_message)
        backIcon=findViewById(R.id.message_system_back_iv)
        messageSystemRv=findViewById(R.id.message_system_rv)
        systemMessageViewModel=ViewModelProvider(this).get(SystemMessageViewModel::class.java)
        backIcon.setOnClickListener{
            finish()
        }
        initData()
    }

    private fun initData() {
        systemList= arrayListOf<MessageSystemItem>()
//        for(i in 0..20){
//            var item:MessageSystemItem=MessageSystemItem(true,"未读系统消息","12-21","消息内容")
//            item.isRead = i <= 10
//            systemList.add(item)
//        }
        systemList=systemMessageViewModel.systemMessageList
        adapter= MessageSystemContentAdapter(systemList,this)
        messageSystemRv.adapter=adapter
        messageSystemRv.layoutManager=LinearLayoutManager(this)
        adapter.setOnItemClickListener(object :MessageSystemContentAdapter.OnItemClickListener{
            //点击一个条目，跳转详情界面(待实现) ,将信息设为已读
            override fun onItemClick(position: Int) {
                //println(position)
                systemList[position].isNotRead=false
                adapter.notifyDataSetChanged()
            }

        })
    }
}