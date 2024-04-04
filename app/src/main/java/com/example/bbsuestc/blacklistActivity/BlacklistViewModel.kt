package com.example.bbsuestc.blacklistActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.blackListContent.BlackListItem

class BlackListViewModel : ViewModel(){

    private val blacklistContent:MutableLiveData<ArrayList<BlackListItem>> = MutableLiveData<ArrayList<BlackListItem>>()
    init{
        //模拟数据
        blacklistContent.value= arrayListOf<BlackListItem>().apply {
            for( i in 0.. 40){
                add(BlackListItem("","河畔用户$i"))
            }
        }
    }
    fun getBlackList():LiveData<ArrayList<BlackListItem>>{
        return blacklistContent
    }
    //移除黑名单，即删除一项
    fun removeFromBlacklist(position : Int){
        blacklistContent.value?.removeAt(position)
        blacklistContent.postValue(blacklistContent.value)
    }
}