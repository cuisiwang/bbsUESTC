package com.example.bbsuestc.friendRequestActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.friendRequestContent.TreatedRequestItem
import com.example.bbsuestc.recyclerViewContents.friendRequestContent.UntreatedRequestItem


class FriendRequestViewModel :ViewModel() {

    private val untreatedRequestList : MutableLiveData<ArrayList<UntreatedRequestItem>> =MutableLiveData<ArrayList<UntreatedRequestItem>>()
    private val treatedRequestList: MutableLiveData<ArrayList<TreatedRequestItem>> =MutableLiveData<ArrayList<TreatedRequestItem>>()

    init{
        //初始化一些数据进行模拟
        untreatedRequestList.value = ArrayList<UntreatedRequestItem>().apply {
            for(i in 0..10){
                add(UntreatedRequestItem("", "河畔用户$i"))
            }
        }
        treatedRequestList.value= ArrayList<TreatedRequestItem>()
    }

    fun getTreatedRequestList(): LiveData<ArrayList<TreatedRequestItem>>{
        return treatedRequestList
    }
    fun getUntreatedRequestList(): LiveData<ArrayList<UntreatedRequestItem>> {
        return untreatedRequestList
    }

    //点击同意的数据更新
    fun confirm(position:Int){
        val untreatedRequest: UntreatedRequestItem? = untreatedRequestList.value?.get(position)
        if (untreatedRequest != null) {
            treatedRequestList.value?.add(TreatedRequestItem(untreatedRequest.untreatedRequestUserIcon,untreatedRequest.untreatedRequestUserName,true) )
        }
        untreatedRequestList.value?.removeAt(position)
        treatedRequestList.postValue(treatedRequestList.value)
        untreatedRequestList.postValue(untreatedRequestList.value)
    }

    //点击拒绝
    fun deny(position: Int){
        val untreatedRequest: UntreatedRequestItem? = untreatedRequestList.value?.get(position)
        if (untreatedRequest != null) {
            treatedRequestList.value?.add(TreatedRequestItem(untreatedRequest.untreatedRequestUserIcon,untreatedRequest.untreatedRequestUserName,false) )
        }
        untreatedRequestList.value?.removeAt(position)
        treatedRequestList.postValue(treatedRequestList.value)
        untreatedRequestList.postValue(untreatedRequestList.value)
    }

    fun deleteTreated(position: Int){
        treatedRequestList.value?.removeAt(position)
        treatedRequestList.postValue(treatedRequestList.value)
    }

    fun ignoreUntreated(position: Int){
        untreatedRequestList.value?.removeAt(position)
        untreatedRequestList.postValue(untreatedRequestList.value)
    }

}