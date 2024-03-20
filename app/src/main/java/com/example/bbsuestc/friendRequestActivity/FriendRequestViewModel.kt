package com.example.bbsuestc.friendRequestActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.FriendRequestContent.TreatedRequestItem
import com.example.bbsuestc.recyclerViewContents.FriendRequestContent.UntreatedRequestItem


class FriendRequestViewModel :ViewModel() {
    //模拟十一条未处理的好友请求数据项
    private val untreatedRequestListInit:ArrayList<UntreatedRequestItem> = arrayListOf<UntreatedRequestItem>().apply {
        for(i in 0..10){
            add(UntreatedRequestItem("","河畔用户"+i.toString()))
        }
    }
    public var untreatedRequestList : MutableLiveData<ArrayList<UntreatedRequestItem>>? =null
    public var treatedRequestList: MutableLiveData<ArrayList<TreatedRequestItem>>? =null
    init{

        untreatedRequestList = MutableLiveData<ArrayList<UntreatedRequestItem>>()
        untreatedRequestList!!.value = ArrayList<UntreatedRequestItem>()

        treatedRequestList=MutableLiveData<ArrayList<TreatedRequestItem>>()
        treatedRequestList!!.value= ArrayList<TreatedRequestItem>()
        //初始化数据
        untreatedRequestList?.value?.addAll(untreatedRequestListInit)
    }
    //点击同意的数据更新
    public fun confirm(position:Int){
        val untreatedRequest: UntreatedRequestItem? = untreatedRequestList?.value?.get(position)
        if (untreatedRequest != null) {

            treatedRequestList?.value?.add(TreatedRequestItem(untreatedRequest.untreatedRequestUserIcon,untreatedRequest.untreatedRequestUserName,true) )
        }
        untreatedRequestList?.value?.removeAt(position)
        untreatedRequestList?.value= untreatedRequestList?.value
        treatedRequestList?.value= treatedRequestList?.value
    }
    //点击拒绝
    public fun deny(position: Int){
        val untreatedRequest: UntreatedRequestItem? = untreatedRequestList?.value?.get(position)
        if (untreatedRequest != null) {
            treatedRequestList?.value?.add(TreatedRequestItem(untreatedRequest.untreatedRequestUserIcon,untreatedRequest.untreatedRequestUserName,false) )
        }
        untreatedRequestList?.value?.removeAt(position)
        untreatedRequestList?.value= untreatedRequestList?.value
        treatedRequestList?.value= treatedRequestList?.value
    }
    public fun deleteTreated(position: Int){
        treatedRequestList?.value?.removeAt(position)
        treatedRequestList?.value= treatedRequestList?.value
    }
    public fun ignoreUntreated(position: Int){


        untreatedRequestList?.value?.removeAt(position)
        untreatedRequestList?.value= untreatedRequestList?.value
    }

}