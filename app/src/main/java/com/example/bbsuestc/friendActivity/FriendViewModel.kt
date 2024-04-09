package com.example.bbsuestc.friendActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.friendContent.FriendItem

class FriendViewModel : ViewModel() {
    // TODO: 所有数据皆为模拟
    private val _friendList = MutableLiveData<ArrayList<FriendItem>>().apply {
        value = arrayListOf<FriendItem>().apply {
            for (i in 0..4) {
                val friendItem = FriendItem("", "河畔用户$i")
                add(friendItem)
            }
        }
    }
    val friendList: LiveData<ArrayList<FriendItem>> = _friendList

    private val _requestCount = MutableLiveData<Int>().apply {
        value = 0
    }
    val requestCount: LiveData<Int>
        get() = _requestCount


}