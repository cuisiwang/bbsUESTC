package com.example.bbsuestc.homeActivity.messages

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupWindow
import android.widget.TextView

import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bbsuestc.R
import com.example.bbsuestc.friendActivity.FriendActivity
import com.example.bbsuestc.recyclerViewContents.MessageContent.MessageContentAdapter
import com.example.bbsuestc.recyclerViewContents.MessageContent.MessageItem
import com.example.bbsuestc.systemMessageActivity.SystemMessageActivity

class MessagesFragment : Fragment() {
    private lateinit var messageToolBar: Toolbar
    private lateinit var messageListRv:RecyclerView
    private lateinit var messageToolbarIv:ImageView
    //private lateinit var message_toolbar_spinner:Spinner
    //好友
    private lateinit var messageFriendIv:ImageView
    private lateinit var view: View
    //系统消息
    private lateinit var messageSystemIv:ImageView
    private lateinit var messageList:ArrayList<MessageItem>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(MessagesViewModel::class.java)

        view = inflater.inflate(R.layout.fragment_messages,container,false)

        messageToolBar=view.findViewById(R.id.toolbar_message)
        messageListRv=view.findViewById(R.id.message_interact_rv)
        messageToolbarIv=view.findViewById(R.id.message_toolbar_add_iv)
        messageFriendIv = view.findViewById(R.id.message_friends_ic)

        messageFriendIv=view.findViewById(R.id.message_friends_ic)

        messageSystemIv=view.findViewById(R.id.message_system_ic)
        //做一个数据
        messageList = arrayListOf<MessageItem>()
        //从viewModel中得到数据
        messageList = notificationsViewModel.messageList.value!!
        messageListRv.adapter= MessageContentAdapter(messageList)
        messageListRv.layoutManager=LinearLayoutManager(activity)
        //点击图片，激活spinner
        messageToolbarIv.setOnClickListener{
            showPopupWindow()
        }
        //点击friend跳转
        messageFriendIv.setOnClickListener{
            val intent = Intent(activity, FriendActivity::class.java)
            startActivity(intent)
        }
        //点击系统消息
        messageSystemIv.setOnClickListener{
            val intent = Intent(activity, SystemMessageActivity::class.java)
            startActivity(intent)
        }
        return view
    }

    private fun showPopupWindow() {
        val pwLayout = LayoutInflater.from(context).inflate(R.layout.popup_window_message,messageToolBar,false)
        val addFriendTv : TextView = pwLayout.findViewById(R.id.message_add_friend_pw_tv)
        val newChatTv : TextView = pwLayout.findViewById(R.id.message_new_chat_pw_tv)
        val pw = PopupWindow(context)
        pw.contentView = pwLayout
        pw.isFocusable = true
        pw.isOutsideTouchable = true
        pw.setBackgroundDrawable(ColorDrawable(0x00000000));
//        pw.width = LinearLayout.LayoutParams.WRAP_CONTENT
//        pwLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
//        val popupWidth = pwLayout.measuredWidth
//        val popupHeight = pwLayout.measuredHeight
//        val location = IntArray(2)
//        message_toolbar_iv.getLocationInWindow(location)
//        val xPos = location[0] - popupWidth
//        val yPos = location[1]
//        pw.showAtLocation(pwLayout,Gravity.NO_GRAVITY,xPos,yPos)
        pw.showAsDropDown(view.findViewById(R.id.message_toolbar_blank_hook))
        //TODO:给两个textview注册点击事件

    }


    override fun onDestroyView() {
        super.onDestroyView()
    }
}