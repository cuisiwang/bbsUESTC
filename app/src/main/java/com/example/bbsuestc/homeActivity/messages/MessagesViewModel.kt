package com.example.bbsuestc.homeActivity.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.messageContent.MessageItem
import java.util.Random

class MessagesViewModel : ViewModel() {

    private val _messageList = MutableLiveData<ArrayList<MessageItem>>().apply {
        value = arrayListOf<MessageItem>().apply {
            for (i in 0..7) {
                add(
                    MessageItem(
                        "",
                        "河畔用户"+ Random().nextInt(653),
                        "1970-01-01",
                        "Messages",
                        i
                    )
                )
            }
        }
    }

    val messageList: LiveData<ArrayList<MessageItem>> = _messageList
}