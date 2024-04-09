package com.example.bbsuestc.friendActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.blackListActivity.BlacklistActivity
import com.example.bbsuestc.friendRequestActivity.FriendRequestActivity
import com.example.bbsuestc.recyclerViewContents.friendContent.FriendContentAdapter
import com.example.bbsuestc.recyclerViewContents.friendContent.FriendItem
import com.example.bbsuestc.utils.fixHeight

class FriendActivity : AppCompatActivity() {
    //好友列表
    private lateinit var friendListRv: RecyclerView

    //搜索输入
    private lateinit var friendSearchInput: EditText

    //返回
    private lateinit var friendBackIv: ImageView

    //用于存储数据
    private lateinit var displayedFriendList: ArrayList<FriendItem>
    private lateinit var friendContentAdapter: FriendContentAdapter
    private val friendViewModel: FriendViewModel by lazy { ViewModelProvider(this)[FriendViewModel::class.java] }

    //黑名单按钮
    private lateinit var blacklistIv: LinearLayout

    //好友请求按钮
    private lateinit var friendRequestIv: LinearLayout
    private lateinit var requestCountTv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend)

        friendListRv = findViewById(R.id.friend_list_rv)
        friendListRv.fixHeight()
        friendSearchInput = findViewById(R.id.friend_search_et)
        friendBackIv = findViewById(R.id.friend_back_iv)
        blacklistIv = findViewById(R.id.friend_blacklist_ly)
        friendRequestIv = findViewById(R.id.friend_request_ly)
        requestCountTv = findViewById(R.id.friend_request_cnt_tv)
        friendBackIv.setOnClickListener {
            finish()
        }

        //点击黑名单跳转
        blacklistIv.setOnClickListener {
            val intent = Intent(this, BlacklistActivity::class.java)
            startActivity(intent)
        }

        //点击好友请求
        friendRequestIv.setOnClickListener {
            val intent = Intent(this, FriendRequestActivity::class.java)
            startActivity(intent)
        }

        initData()
        setEditTextSearchListener();
    }

    private fun setEditTextSearchListener() {
        friendSearchInput.addTextChangedListener(object : TextWatcher {
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
        val input: String = friendSearchInput.text.toString()
        displayedFriendList.clear()
        for (i in 0..<friendViewModel.friendList.value!!.size) {
            val item: FriendItem = friendViewModel.friendList.value!![i]
            val name: String = item.userName
            // TODO: 查询逻辑有问题 
            if (name.contains(input)) {
                displayedFriendList.add(item)
            }

        }
        friendContentAdapter.notifyDataSetChanged()
    }

    private fun initData() {
        displayedFriendList = arrayListOf()
        displayedFriendList = friendViewModel.friendList.value!!
        friendContentAdapter = FriendContentAdapter(displayedFriendList)
        friendListRv.adapter = friendContentAdapter
        friendListRv.layoutManager = LinearLayoutManager(this)

        if(friendViewModel.requestCount.value == 0){
            requestCountTv.text = "0"
        }else if (friendViewModel.requestCount.value!! <= 99){
            requestCountTv.setBackgroundResource(R.drawable.ic_message_dot)
            requestCountTv.setTextColor(resources.getColor(R.color.white))
            requestCountTv.text = friendViewModel.requestCount.value.toString()
        }else{
            requestCountTv.setBackgroundResource(R.drawable.ic_message_dot)
            requestCountTv.setTextColor(resources.getColor(R.color.white))
            requestCountTv.text = "99+"
            requestCountTv.textSize = 10F
        }
    }
}