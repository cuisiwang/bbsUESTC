package com.example.bbsuestc.blackListActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.blackListContent.BlackListItems

class BlackListViewModel : ViewModel() {
    // TODO: 数据为模拟 
    private val _blackListLiveData = MutableLiveData<ArrayList<BlackListItems>>().apply {
        value = arrayListOf<BlackListItems>().apply {
            for (i in 1..30) {
                add(BlackListItems("", "河畔用户$i"))
            }
        }
    }

    val blackList: LiveData<ArrayList<BlackListItems>> = _blackListLiveData

    fun removeFromBlacklist(position: Int) {
        _blackListLiveData.value?.removeAt(position)
        _blackListLiveData.postValue(_blackListLiveData.value)
    }

}