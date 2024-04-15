package com.example.bbsuestc.postsRelativeActivity

data class PostsRelativeTabItem(
    val title:String,
    //是否有未读消息
    val haveNotice:Boolean,
    val noticeCnt:Int

)