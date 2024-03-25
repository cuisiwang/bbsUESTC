package com.example.bbsuestc.blacklistActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.blacklistContent.BlacklistItem

class BlacklistViewModel : ViewModel(){

    private val blacklistContent:MutableLiveData<ArrayList<BlacklistItem>> = MutableLiveData<ArrayList<BlacklistItem>>()
    init{
        //模拟数据
        blacklistContent.value= arrayListOf<BlacklistItem>().apply {
            for( i in 0.. 40){
                add(BlacklistItem("","河畔用户$i"))
            }
        }
    }
    fun getBlackList():LiveData<ArrayList<BlacklistItem>>{
        return blacklistContent
    }
    //移除黑名单，即删除一项
    fun removeFromBlacklist(position : Int){
        blacklistContent.value?.removeAt(position)
        blacklistContent.postValue(blacklistContent.value)
    }
}