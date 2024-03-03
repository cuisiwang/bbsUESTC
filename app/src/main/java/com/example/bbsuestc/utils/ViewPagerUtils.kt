package com.example.bbsuestc.utils

import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import android.widget.Toast
import java.lang.Exception
import java.lang.reflect.Field

fun ViewPager2.adjustScrollSensitivity(vp : ViewPager2, factor: Int) {
    //通过反射动态设置ViewPager2 灵敏度
    try {
        val recyclerViewField: Field = ViewPager2::class.java.getDeclaredField("mRecyclerView")
        recyclerViewField.isAccessible = true
        val recyclerView = recyclerViewField.get(vp) as RecyclerView
        val touchSlopField: Field = RecyclerView::class.java.getDeclaredField("mTouchSlop")
        touchSlopField.isAccessible = true
        val touchSlop = touchSlopField.get(recyclerView) as Int
        touchSlopField.set(recyclerView, touchSlop * factor)
    } catch (e:Exception) {
        e.printStackTrace()
        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show()
    }


}