package com.example.bbsuestc.systemMessageActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.messageSystemContent.MessageSystemItem

class SystemMessageViewModel : ViewModel() {

    private val _systemMessageList : MutableLiveData<ArrayList<MessageSystemItem>> = MutableLiveData<ArrayList<MessageSystemItem>>().apply {
        value=initSystemMessageList()
    }

    val systemMessageList:LiveData<ArrayList<MessageSystemItem>> = _systemMessageList
    private fun initSystemMessageList(): ArrayList<MessageSystemItem>? {
        val list:ArrayList<MessageSystemItem> = arrayListOf<MessageSystemItem>().apply {
            for(i in 1.. 50){
                add(MessageSystemItem(i<25 ,"未读系统消息","12-21","消息内容"))
            }
        }
        return list
    }
    fun readMessage(position:Int){
        //重置消息为已读
        _systemMessageList.value?.get(position)?.isNotRead  =false
        _systemMessageList.postValue(_systemMessageList.value)
    }

}