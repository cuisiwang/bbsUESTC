package com.example.bbsuestc.homeActivity.messages

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Spinner

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.friendActivity.FriendActivity
import com.example.bbsuestc.newPostActivity.NewPostActivity
import com.example.bbsuestc.recyclerViewContents.MessageContent.MessageContentAdapter
import com.example.bbsuestc.recyclerViewContents.MessageContent.MessageItem

class MessagesFragment : Fragment() {
    private  lateinit var toolbar: Toolbar
    private lateinit var rv:RecyclerView
    private lateinit var message_toolbar_iv:ImageView
    private lateinit var message_toolbar_spinner:Spinner
    //好友
    private lateinit var message_friend_iv:ImageView
    override fun onCreateView( 
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(MessagesViewModel::class.java)

        val view: View = inflater.inflate(R.layout.fragment_messages,container,false)

        toolbar=view.findViewById(R.id.toolbar_message)
        rv=view.findViewById(R.id.message_interact_rv)
        message_toolbar_iv=view.findViewById(R.id.message_toolbar_add_iv)
        message_friend_iv=view.findViewById(R.id.message_friends_ic)
        message_toolbar_spinner=view.findViewById(R.id.message_toolbar_spinner)
        //做一个数据
        val data = arrayListOf<MessageItem>()
        for(i in 0..130){
            data.add(MessageItem("","河畔用户1","12-01","有一条信息有一条信息有一条信息有一条信息有一条信息有一条信息",i))
        }
        rv.adapter= MessageContentAdapter(data)
        rv.layoutManager=LinearLayoutManager(activity)
        //点击图片，激活spinner
        message_toolbar_iv.setOnClickListener{message_toolbar_spinner.performClick()}
//        toolbar.inflateMenu(R.menu.message_menu)  TODO：使用spinner
//        toolbar.overflowIcon = resources.getDrawable(R.drawable.ic_add)
        //点击friend跳转
        message_friend_iv.setOnClickListener{
            val intent = Intent(activity, FriendActivity::class.java)
            startActivity(intent)
        }
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }
}