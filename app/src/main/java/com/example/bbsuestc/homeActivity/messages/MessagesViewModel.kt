package com.example.bbsuestc.homeActivity.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.messageContent.MessageItem

class MessagesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    private val _MessageList= MutableLiveData<ArrayList<MessageItem>>().apply {
        val list:ArrayList<MessageItem> = arrayListOf<MessageItem>().apply {
            for(i in 1..40){
                add((MessageItem("","河畔用户1","12-01","有一条信息有一条信息有一条信息有一条信息有一条信息有一条信息",i)))
            }
        }
        value=list
    }
    val text: LiveData<String> = _text
    val messageList:MutableLiveData<ArrayList<MessageItem>> = _MessageList
}