package com.example.bbsuestc.blackListActivity

import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.blackListContent.BlacklistItem

class BlackListViewModel : ViewModel(){
    val blacklistContent:ArrayList<BlacklistItem> = arrayListOf<BlacklistItem>().apply {
        for( i in 0.. 40){
            add(BlacklistItem("","黑名单用户1"))
        }
    }


}