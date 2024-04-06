package com.example.bbsuestc.blacklistActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.blackListContent.BlackListItems

class BlackListViewModel : ViewModel() {
    private val _blackListLiveData = MutableLiveData<ArrayList<BlackListItems>>().apply {
        value = initBlackList()
    }

    val blackList: LiveData<ArrayList<BlackListItems>> = _blackListLiveData


    private fun initBlackList(): ArrayList<BlackListItems>? {
        val list = arrayListOf<BlackListItems>()
        for (i in 1 .. 50){
            list.add(BlackListItems("","河畔用户$i"))
        }
        return list
    }
    fun removeFromBlacklist(position:Int){
        _blackListLiveData.value?.removeAt(position)
        _blackListLiveData.postValue(_blackListLiveData.value)
    }

}