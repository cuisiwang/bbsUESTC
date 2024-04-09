package com.example.bbsuestc.homeActivity.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.messageContent.MessageItem

class MessagesViewModel : ViewModel() {

    private val _messageList = MutableLiveData<ArrayList<MessageItem>>().apply {
        value = arrayListOf<MessageItem>().apply {
            for (i in 0..20) {
                add(
                    MessageItem(
                        "",
                        "河畔用户1",
                        "12-01",
                        "有一条信息有一条信息有一条信息有一条信息有一条信息有一条信息",
                        i
                    )
                )
            }
        }
    }

    val messageList: LiveData<ArrayList<MessageItem>> = _messageList
}