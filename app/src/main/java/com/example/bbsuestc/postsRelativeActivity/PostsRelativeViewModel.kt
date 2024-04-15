package com.example.bbsuestc.postsRelativeActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.postsRelativeContent.PostsRelativeItem


class PostsRelativeViewModel : ViewModel() {
    public var tabItems:ArrayList<PostsRelativeTabItem> = arrayListOf<PostsRelativeTabItem>().apply {
        add(PostsRelativeTabItem("全部",true,23))
        add(PostsRelativeTabItem("回复",true,58))
        add(PostsRelativeTabItem("@",false,0))
        add(PostsRelativeTabItem("点评",true,6))
        add(PostsRelativeTabItem("评分",true,6))
    }

    private val _postsRelativeList=MutableLiveData<ArrayList<PostsRelativeItem>>().apply {
        value= arrayListOf<PostsRelativeItem>().apply {
            for(i in 1..10){
                add(PostsRelativeItem.PostsRelativeReplyItem("用户1234","",i,"帖子123","前排看看",60,1))
            }
        }
    }
    val postsRelativeList:LiveData<ArrayList<PostsRelativeItem>> = _postsRelativeList


}