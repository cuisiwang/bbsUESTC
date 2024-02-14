package com.example.bbsuestc.homeActivity.plates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlatesViewModel : ViewModel() {

    val plateGroup1 = PlateGroup("前程似锦", listOf(
        Plate("就业创业", 0),
        Plate("出国留学", 1),
        Plate("公考选调", 2),
        Plate("保研考研", 3),
    ))

    val plateGroup2 = PlateGroup("成电校园", listOf(
        Plate("部门直通车", 4),
        Plate("水手之家", 5),
        Plate("校园热点", 6),
        Plate("情感专区", 7),
    ))

    val plateGroups = listOf(
        plateGroup1,
        plateGroup2
    )
}