package com.example.bbsuestc.friendActivity

import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.friendContent.FriendItem

class FriendViewModel : ViewModel(){

    private val _FriendList = arrayListOf<FriendItem>().apply {
        for(i in 0..400){
            val friendItem=FriendItem("","河畔用户"+i.toString())
            add(friendItem)
        }
    }
    private val _FriendListAll = arrayListOf<FriendItem>().apply {
        for(i in 0..400){
            val friendItem=FriendItem("","河畔用户"+i.toString())
            add(friendItem)
        }
    }
    val friendList:ArrayList<FriendItem> = _FriendList
    val friendListAll: ArrayList<FriendItem> = _FriendListAll

}