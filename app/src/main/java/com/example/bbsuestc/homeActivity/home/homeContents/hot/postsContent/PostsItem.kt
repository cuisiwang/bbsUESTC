package com.example.bbsuestc.homeActivity.home.homeContents.hot.postsContent

data class PostsItem(
    val posterIcon : String,
    val posterID : String,
    val postTime : String, //时间不确定格式
    val postTitle : String,
    val postContent : String,
    val plates : String,
    val viewers : Int,
    val comments : Int
    )