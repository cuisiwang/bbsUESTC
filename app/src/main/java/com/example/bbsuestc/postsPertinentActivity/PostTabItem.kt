package com.example.bbsuestc.postsPertinentActivity

data class PostTabItem(
    val title:String,
    //是否有未读消息
    val haveNotice:Boolean,
    val noticeCnt:Int

) {
}