package com.example.bbsuestc.friendRequestActivity

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.friendRequestContent.TreatedRequestItem
import com.example.bbsuestc.recyclerViewContents.friendRequestContent.UntreatedRequestItem


class FriendRequestViewModel : ViewModel() {

    private val _untreatedRequestList = MutableLiveData<ArrayList<UntreatedRequestItem>>().apply {
        value = arrayListOf<UntreatedRequestItem>().apply {
            for (i in 0..10) {
                add(UntreatedRequestItem("", "河畔用户$i"))
            }
        }
    }
    private val _treatedRequestList = MutableLiveData<ArrayList<TreatedRequestItem>>().apply {
        value = arrayListOf<TreatedRequestItem>()
    }

    val untreatedRequestList: LiveData<ArrayList<UntreatedRequestItem>> = _untreatedRequestList
    val treatedRequestList: LiveData<ArrayList<TreatedRequestItem>> = _treatedRequestList


    //点击同意的数据更新
    fun confirm(position: Int) {
        val untreatedRequest: UntreatedRequestItem? = _untreatedRequestList.value?.get(position)
        if (untreatedRequest != null) {
            _treatedRequestList.value?.add(
                TreatedRequestItem(
                    untreatedRequest.untreatedRequestUserIcon,
                    untreatedRequest.untreatedRequestUserName,
                    true
                )
            )
        }
        _untreatedRequestList.value?.removeAt(position)
        _treatedRequestList.postValue(treatedRequestList.value)
        _untreatedRequestList.postValue(untreatedRequestList.value)
    }

    //点击拒绝
    fun deny(position: Int) {
        val untreatedRequest: UntreatedRequestItem? = _untreatedRequestList.value?.get(position)
        if (untreatedRequest != null) {
            _treatedRequestList.value?.add(
                TreatedRequestItem(
                    untreatedRequest.untreatedRequestUserIcon,
                    untreatedRequest.untreatedRequestUserName,
                    false
                )
            )
        }
        _untreatedRequestList.value?.removeAt(position)
        _treatedRequestList.postValue(treatedRequestList.value)
        _untreatedRequestList.postValue(untreatedRequestList.value)
    }

    fun deleteTreated(position: Int) {
        _treatedRequestList.value?.removeAt(position)
        _treatedRequestList.postValue(treatedRequestList.value)
    }

    fun ignoreUntreated(position: Int) {
        _untreatedRequestList.value?.removeAt(position)
        _untreatedRequestList.postValue(untreatedRequestList.value)
    }

}