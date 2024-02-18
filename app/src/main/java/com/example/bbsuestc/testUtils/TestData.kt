package com.example.bbsuestc.testUtils

import com.example.bbsuestc.recyclerViewContents.postsContent.PostsItem

//用于生成测试数据，
//TODO 发行版应删除
class TestData {
    companion object {
        fun postData(): ArrayList<PostsItem> {
            val data = arrayListOf<PostsItem>()
            for(i in 0..10){
                data.add(
                    PostsItem("","这是发帖人ID","2022-12-22","这是帖子的标题",
                        "这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容",
                        "校园生活",114514,191)
                )
            }
            return data
        }

        fun homeHotHeaderData(): ArrayList<String> {
            val itemNames : ArrayList<String> = arrayListOf()
            itemNames.apply {
                add("论坛服务")
                add("校历")
                add("服务大厅")
                add("网上报修")
                add("一卡通查询")
                add("校车时刻表")
                add("统计数据")
            }

            return itemNames
        }

        fun plateHotHeaderData(): ArrayList<String> {
            val itemNames : ArrayList<String> = arrayListOf()
            itemNames.apply {
                add("版规")
                add("招聘信息发布栏")
                add("藏经阁")
                add("实习信息发布栏")
                add("职场交流")
            }

            return itemNames
        }


    }

    object PlateInfo {
        val mod = "陈瑞"
        val postsToday = 201
        val posts = 114514
    }
}