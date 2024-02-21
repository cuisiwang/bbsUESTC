package com.example.bbsuestc.friendActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.recyclerViewContents.FriendContent.FriendContentAdapter
import com.example.bbsuestc.recyclerViewContents.FriendContent.FriendItem
import com.example.bbsuestc.recyclerViewContents.postsContent.PostsContentAdapter
import com.google.android.material.textfield.TextInputEditText

class FriendActivity : AppCompatActivity() {
    //好友列表
    private lateinit var friend_list:RecyclerView
    //搜索输入
    private lateinit var friend_search_input:TextInputEditText
    //返回
    private lateinit var friend_back_iv:ImageView
    //用于存储数据
    private lateinit var data:ArrayList<FriendItem>
    private lateinit var data1:ArrayList<FriendItem>
    private lateinit var adapter:FriendContentAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)
        friend_list=findViewById(R.id.friend_list_rv)
        friend_search_input=findViewById(R.id.friend_search_tf)
        friend_back_iv=findViewById(R.id.friend_back_iv)
        friend_back_iv.setOnClickListener{
            finish()
        }

        initData()
        editTextSearchListener();
    }

    private fun editTextSearchListener() {
        friend_search_input.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                search()
            }

        });

    }

    private fun search() {
        val input:String= friend_search_input.text.toString()
        data.clear()
        for(i in 0..data1.size-1){
            val item:FriendItem=data1[i]
            val name:String=item.userName
            if(name.contains(input)){
                data.add(item)
            }

        }
        adapter.notifyDataSetChanged()
    }

    private fun initData(){

        data= arrayListOf<FriendItem>()
        data1= arrayListOf<FriendItem>()
        for(i in 0..40){
            val friendItem=FriendItem("","河畔用户"+i.toString())
            data.add(friendItem)
            data1.add(friendItem)
        }
        adapter=FriendContentAdapter(data)
        friend_list.adapter=adapter
        friend_list.layoutManager=LinearLayoutManager(this)
    }
}