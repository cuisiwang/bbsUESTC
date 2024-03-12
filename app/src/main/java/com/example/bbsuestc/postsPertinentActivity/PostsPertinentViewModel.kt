package com.example.bbsuestc.postsPertinentActivity

import androidx.lifecycle.ViewModel

class PostsPertinentViewModel : ViewModel() {
    public var tabItems:ArrayList<PostTabItem> = arrayListOf<PostTabItem>().apply {
        add(PostTabItem("全部",true,23))
        add(PostTabItem("回复",true,58))
        add(PostTabItem("@",false,0))
        add(PostTabItem("点评",true,6))
        add(PostTabItem("评分",true,6))
    }
}