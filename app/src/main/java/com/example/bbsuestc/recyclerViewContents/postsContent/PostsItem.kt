package com.example.bbsuestc.recyclerViewContents.postsContent

data class PostsItem(
    val posterIcon : String,
    val posterID : String,
    val postTime : String,
    val postTitle : String,
    val postContent : String,
    val plates : String,
    val viewers : Int,
    val comments : Int
    )