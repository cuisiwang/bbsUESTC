package com.example.bbsuestc.friendRequestActivity

import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.friendRequestContent.UntreatedRequestItem

class FriendRequestViewModel :ViewModel() {
    //模拟十一条未处理的好友请求数据项
    val untreatedRequestList:ArrayList<UntreatedRequestItem> = arrayListOf<UntreatedRequestItem>().apply {
        for(i in 0..10){
            add(UntreatedRequestItem("","河畔用户"+i.toString()))
        }
    }
}