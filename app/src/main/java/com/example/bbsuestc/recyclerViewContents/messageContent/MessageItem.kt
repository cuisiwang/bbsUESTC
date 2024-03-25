package com.example.bbsuestc.recyclerViewContents.messageContent

data class MessageItem(
    val userIcon : String,
    val userID : String,
    val time : String, //时间不确定格式
    val messageContent : String,
    val unreadCount : Int
)