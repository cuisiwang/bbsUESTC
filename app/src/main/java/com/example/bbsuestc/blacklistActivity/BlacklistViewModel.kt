package com.example.bbsuestc.blacklistActivity

import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.BlacklistContent.BlacklistItem

class BlacklistViewModel : ViewModel(){
    val blacklistContent:ArrayList<BlacklistItem> = arrayListOf<BlacklistItem>().apply {
        for( i in 0.. 40){
            add(BlacklistItem("","河畔用户1"))
        }
    }


}