package com.example.bbsuestc.homeActivity.plates

import androidx.lifecycle.ViewModel
import com.example.bbsuestc.recyclerViewContents.platesContent.Plate
import com.example.bbsuestc.recyclerViewContents.platesContent.PlateGroup

class PlatesViewModel : ViewModel() {

    private var index = 0

    private val plateGroup1 = PlateGroup("前程似锦", listOf(
        Plate("就业创业", index++),
        Plate("出国留学", index++),
        Plate("公考选调", index++),
        Plate("保研考研", index++),
    ))

    private val plateGroup2 = PlateGroup("成电校园", listOf(
        Plate("部门直通车", index++),
        Plate("水手之家", index++),
        Plate("校园热点", index++),
        Plate("鹊桥", index++),
        Plate("情感专区",index++),
        Plate("吃喝玩乐",index++),
        Plate("成电锐评",index++),
        Plate("交通出行",index++),
        Plate("同城同乡",index++),
        Plate("毕业感言",index++),
        Plate("新生专区",index++),
    ))

    private val plateGroup3 = PlateGroup("科技学术", listOf(
        Plate("学术交流",index++),
        Plate("考试专区",index++),
        Plate("程序员之家",index++),
        Plate("自然科学",index++),
        Plate("电子数码",index++),
        Plate("IC电设",index++),
    ))

    private val plateGroup4 = PlateGroup("生活信息", listOf(
        Plate("生活专区",index++),
        Plate("房屋租赁",index++),
        Plate("店铺专区",index++),
        Plate("失物招领",index++),
        Plate("拼车同行",index++),
        Plate("兼职信息",index++),
    ))

    private val plateGroup5 = PlateGroup("休闲娱乐", listOf(
        Plate("跑步家园",index++),
        Plate("成电骑迹",index++),
        Plate("军事国防",index++),
        Plate("视觉艺术",index++),
        Plate("影视天地",index++),
        Plate("音乐空间",index++),
        Plate("体坛风云",index++),
        Plate("人文墨客",index++),
        Plate("情系舞缘",index++),
        Plate("动漫时代",index++),
        Plate("投资理财",index++),
        Plate("社团交流中心",index++),
    ))

    val plateGroups = listOf(
        plateGroup1,
        plateGroup2,
        plateGroup3,
        plateGroup4,
        plateGroup5
    )
}