package com.example.bbsuestc.homeActivity.messages

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.recyclerViewContents.MessageContent.MessageContentAdapter
import com.example.bbsuestc.recyclerViewContents.MessageContent.MessageItem
import com.example.bbsuestc.recyclerViewContents.postsContent.PostsItem

class MessagesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(MessagesViewModel::class.java)

        val view: View = inflater.inflate(R.layout.fragment_messages,container,false)

//        val toolbar:Toolbar=view.findViewById(R.id.toolbar_message) TODO: 参考别的类，将控件声明独立到方法外
//        val rv:RecyclerView=view.findViewById(R.id.friendRv)
        //做一个数据
        val data = arrayListOf<MessageItem>()
        for(i in 0..130){
            data.add(MessageItem("","河畔用户1","12-01","有一条信息有一条信息有一条信息有一条信息有一条信息有一条信息",i))
        }
        rv.adapter= MessageContentAdapter(data)
        rv.layoutManager=LinearLayoutManager(activity)
//        toolbar.inflateMenu(R.menu.message_menu)  TODO：使用spinner
//        toolbar.overflowIcon = resources.getDrawable(R.drawable.ic_add)
        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }
}