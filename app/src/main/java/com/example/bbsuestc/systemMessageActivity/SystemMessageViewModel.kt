package com.example.bbsuestc.systemMessageActivity

import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.messageSystemContent.MessageSystemItem

class SystemMessageViewModel : ViewModel() {
    val systemMessageList= arrayListOf<MessageSystemItem>(MessageSystemItem(true,"未读系统消息","12-21","消息内容"),
        MessageSystemItem(true,"未读系统消息","12-21","消息内容"),
                MessageSystemItem(true,"未读系统消息","12-21","消息内容"),
        MessageSystemItem(true,"未读系统消息","12-21","消息内容"),
        MessageSystemItem(true,"未读系统消息","12-21","消息内容"),
        MessageSystemItem(false,"未读系统消息","12-21","消息内容"))

}