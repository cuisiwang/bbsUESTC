package com.example.bbsuestc.friendActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.blacklistActivity.BlacklistActivity
import com.example.bbsuestc.friendRequestActivity.FriendRequestActivity
import com.example.bbsuestc.recyclerViewContents.friendContent.FriendContentAdapter
import com.example.bbsuestc.recyclerViewContents.friendContent.FriendItem

class FriendActivity : AppCompatActivity() {
    //好友列表
    private lateinit var friendListRv:RecyclerView
    //搜索输入
    private lateinit var friendSearchInput:EditText
    //返回
    private lateinit var friendBackIv:ImageView
    //用于存储数据
    private lateinit var friendList:ArrayList<FriendItem>
    private lateinit var friendListAll:ArrayList<FriendItem>
    private lateinit var friendContentAdapter:FriendContentAdapter
    private lateinit var friendViewModel: FriendViewModel
    //黑名单按钮
    private lateinit var blacklistIv:ImageView
    //好友请求按钮
    private lateinit var friendRequestIv:ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)
        friendListRv=findViewById(R.id.friend_list_rv)
        friendSearchInput=findViewById(R.id.friend_search_et)
        friendBackIv=findViewById(R.id.friend_back_iv)

        blacklistIv=findViewById(R.id.friend_blacklist_iv)

        friendRequestIv=findViewById(R.id.friend_request_iv)

        friendViewModel=ViewModelProvider(this).get(FriendViewModel::class.java)
        friendBackIv.setOnClickListener{
            finish()
        }

        //点击黑名单跳转
        blacklistIv.setOnClickListener{
            val intent = Intent(this, BlacklistActivity::class.java)
            startActivity(intent)
        }

        //点击好友请求
        friendRequestIv.setOnClickListener{
            val intent=Intent(this,FriendRequestActivity::class.java)
            startActivity(intent)
        }

        initData()
        editTextSearchListener();
    }

    private fun editTextSearchListener() {
        friendSearchInput.addTextChangedListener(object :TextWatcher{
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
        val input:String= friendSearchInput.text.toString()
        friendList.clear()
        for(i in 0..friendListAll.size-1){
            val item:FriendItem=friendListAll[i]
            val name:String=item.userName
            if(name.contains(input)){
                friendList.add(item)
            }

        }
        friendContentAdapter.notifyDataSetChanged()
    }

    private fun initData(){

        friendList= arrayListOf<FriendItem>()
        friendListAll= arrayListOf<FriendItem>()
        friendList=friendViewModel.friendList
        friendListAll=friendViewModel.friendListAll
        friendContentAdapter=FriendContentAdapter(friendList)
        friendListRv.adapter=friendContentAdapter
        friendListRv.layoutManager=LinearLayoutManager(this)
    }
}