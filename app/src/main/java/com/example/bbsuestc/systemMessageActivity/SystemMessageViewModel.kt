package com.example.bbsuestc.systemMessageActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.messageSystemContent.MessageSystemItem

class SystemMessageViewModel : ViewModel() {

    private val _systemMessageList = MutableLiveData<ArrayList<MessageSystemItem>>().apply {
        value=arrayListOf<MessageSystemItem>().apply {
            add(MessageSystemItem(true,"未读系统消息","12-21","消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容消息内容"))
            for(i in 1.. 10){
                add(MessageSystemItem(i<7 ,"未读系统消息","12-21","消息内容"))
            }
        }
    }

    val systemMessageList:LiveData<ArrayList<MessageSystemItem>> = _systemMessageList
    fun readMessage(position:Int){
        //重置消息为已读
        _systemMessageList.value?.get(position)?.isNotRead  =false
        _systemMessageList.postValue(_systemMessageList.value)
    }

}