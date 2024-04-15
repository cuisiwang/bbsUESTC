package com.example.bbsuestc.homeActivity.messages

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.friendActivity.FriendActivity
import com.example.bbsuestc.postsRelativeActivity.PostsRelativeActivity
import com.example.bbsuestc.recyclerViewContents.messageContent.MessageContentAdapter
import com.example.bbsuestc.recyclerViewContents.messageContent.MessageItem
import com.example.bbsuestc.systemMessageActivity.SystemMessageActivity
import com.example.bbsuestc.utils.fixHeight

class MessagesFragment : Fragment() {
    private lateinit var messageToolBar: Toolbar
    private lateinit var messageListRv: RecyclerView
    private lateinit var messageToolbarIv: ImageView

    //好友
    private lateinit var messageFriendIv: LinearLayout
    private lateinit var view: View

    //系统消息
    private lateinit var messageSystemIv: LinearLayout

    private lateinit var messageList: ArrayList<MessageItem>
    private val notificationsViewModel by lazy {
        ViewModelProvider(this)[MessagesViewModel::class.java]
    }

    //帖子相关
    private lateinit var postsRelativeIv: LinearLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_messages, container, false)

        messageToolBar = view.findViewById(R.id.toolbar_message)
        messageListRv = view.findViewById(R.id.message_interact_rv)
        messageToolbarIv = view.findViewById(R.id.message_toolbar_add_iv)
        messageFriendIv = view.findViewById(R.id.message_friends_iv)
        messageSystemIv = view.findViewById(R.id.message_system_iv)
        postsRelativeIv = view.findViewById(R.id.message_post_ly)

        //做一个数据
        messageList = arrayListOf<MessageItem>()

        //点击图片，激活spinner
        messageToolbarIv.setOnClickListener {
            showPopupWindow()
        }
        //点击friend跳转
        messageFriendIv.setOnClickListener {
            val intent = Intent(activity, FriendActivity::class.java)
            startActivity(intent)
        }
        //点击系统消息
        messageSystemIv.setOnClickListener {
            val intent = Intent(activity, SystemMessageActivity::class.java)
            startActivity(intent)
        }
        //点击帖子相关
        postsRelativeIv.setOnClickListener{
            val intent=Intent(activity,PostsRelativeActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //从viewModel中得到数据
        messageListRv.fixHeight()
        messageList = notificationsViewModel.messageList.value!!
        messageListRv.adapter = MessageContentAdapter(messageList)
        messageListRv.layoutManager = LinearLayoutManager(activity)
    }

    private fun showPopupWindow() {
        val pwLayout = LayoutInflater.from(context)
            .inflate(R.layout.popup_window_message, messageToolBar, false)
        val addFriendTv: TextView = pwLayout.findViewById(R.id.message_add_friend_pw_tv)
        val newChatTv: TextView = pwLayout.findViewById(R.id.message_new_chat_pw_tv)
        val pw = PopupWindow(context)
        pw.contentView = pwLayout
        pw.isFocusable = true
        pw.isOutsideTouchable = true
        pw.setBackgroundDrawable(ColorDrawable(0x00000000))
        pw.showAsDropDown(view.findViewById(R.id.message_toolbar_add_iv))
        //TODO:给两个textview注册点击事件

    }


    override fun onDestroyView() {
        super.onDestroyView()
    }
}