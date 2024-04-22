package com.example.bbsuestc.testUtils

import com.example.bbsuestc.plateDetailActivity.PinnedPost
import com.example.bbsuestc.recyclerViewContents.postsContent.PostsItem

//用于生成测试数据，
//TODO 发行版应删除
class TestData {
    companion object {
        fun postData(): ArrayList<PostsItem> {
            val data = arrayListOf<PostsItem>()
//            for(i in 0..9){
//                data.add(
//                    PostsItem("","这是发帖人ID","2022-12-22","这是帖子的标题",
//                        "这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容这是内容",
//                        "校园生活",114514,191)
//                )
//            }
            data.add(PostsItem("","细胞质不机智","2024-03-29","河畔十六周年庆要来力","时间紧任务重，要开始准备了！","水手之家",683,7))
            data.add(PostsItem("","正二SAMA","2024-03-29","麻烦大家随便私聊点消息给我","完全没有私聊消息捏，都没法测试这一块[表情]","水手之家",395,3))
            data.add(PostsItem("","豆豆","2024-03-27","怎么散水还有税的啊","。。。","水手之家",231,5))
            data.add(PostsItem("","Dylan0623","2023-04-17","来来来，医保报销全流程汇总","本帖最后由 Dylan0623 于 2023-4-17 22:48 编辑\n" +
                    "前言\n" +
                    "注：截至发帖时间，2022级及以前学生可以报销医保，23级学生还没开启报销通道。本帖主要适用于校外转诊门诊的学生。\n" +
                    "清水河报销地点：校医院5楼508医保办\n" +
                    "沙河报销地点：住院部一楼医保办\n" +
                    "报销时间：目前四月份都是周一和周三，具体时段看后续图片（每个月上班时间应该不一样，","校园热点",32914,56))
            data.add(PostsItem("","mafared","2023-12-02","求助港科大HKUST的IT项目学长学姐","24fall入学，本科cs，主要申港新的计算机授课硕，最近收到了港科大的IT项目offer，想问下就读过这个项目的学长学姐一些问题。","出国留学",674,6))
            data.add(PostsItem("","mousexia","2023-09-26", "企业黑名单【更新至2017年】禁止mark","近日看到kyle发帖建议建立一个论坛的企业黑名单，看到很多童鞋都十分支持[s:46]    ，所以现在试验开设一个企业黑名单专栏。前面和去年都有很多公司在最后毕业的时候违约，导致很多同学没工作就直接失业了，其中不乏一些大公司、名企。企业可以很轻松的承担学生个人违约所带来的影响，但是，我们学生承担不起","就业创业",162491,247))
            data.add(PostsItem("","账号未注销","2023-06-24","高考分数能读医学八年制哪怕是211也要去读","高考分数出来了，楼主有很多话要说。高考分数就是一笔财富，选大学就是买股票。随着中国人口老龄化的进程，医学专业的股价会越来越高。","成电锐评",2491,57))
            data.add(PostsItem("","sliany","2023-12-01","请问纯理论类课题可以申请大创吗？","如题，求教，谢谢大家。","学术交流",251,4))
            return data
        }

//        fun postData1(){
//            val data = arrayListOf<PostsItem>()
//            data.add()
//        }

        fun pinnedPosts(): ArrayList<PinnedPost> {
            val data = ArrayList<PinnedPost>()
            for (i in 0..1) {
                data.add(
                    PinnedPost("这是一个被置顶的帖子")
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
                add("招聘信息")
                add("藏经阁")
                add("实习信息")
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