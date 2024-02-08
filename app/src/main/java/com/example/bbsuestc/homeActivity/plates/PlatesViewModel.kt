package com.example.bbsuestc.homeActivity.plates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PlatesViewModel : ViewModel() {

    val plateGroup1 = PlateGroup("前程似锦", listOf(
        Plate("就业创业"),
        Plate("出国留学"),
        Plate("公考选调"),
        Plate("保研考研"),
    ))

    val plateGroup2 = PlateGroup("成电校园", listOf(
        Plate("部门直通车"),
        Plate("水手之家"),
        Plate("校园热点"),
        Plate("情感专区"),
    ))

    val plateGroups = listOf(
        plateGroup1,
        plateGroup2
    )
}