package com.example.bbsuestc.friendActivity

import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.friendContent.FriendItem

class FriendViewModel : ViewModel() {

    private val _friendList = arrayListOf<FriendItem>().apply {
        for (i in 0..40) {
            val friendItem = FriendItem("", "河畔用户$i")
            add(friendItem)
        }
    }
    private val _friendListAll = arrayListOf<FriendItem>().apply {
        for (i in 0..40) {
            val friendItem = FriendItem("", "河畔用户$i")
            add(friendItem)
        }
    }
    val friendList: ArrayList<FriendItem> = _friendList
    val friendListAll: ArrayList<FriendItem> = _friendListAll

}