package com.example.bbsuestc.recyclerViewContents.MessageSystemContent

data class MessageSystemItem (
    var isNotRead:Boolean,
    val messageTitle:String,
    val messageTime:String,
    val messageContent:String
)