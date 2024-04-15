package com.example.bbsuestc.recyclerViewContents.postsRelativeContent

//暂时认为需要多个数据类，封装一下,后续可改
sealed class PostsRelativeItem{
    data class PostsRelativeReplyItem(val userName:String,
                                      val userIcon:String,
                                      val daysBefore:Int,
                                      val postName:String,
                                      val replyContent:String,
                                      val likeCount:Int,
                                      val viewType:Int):PostsRelativeItem()
}