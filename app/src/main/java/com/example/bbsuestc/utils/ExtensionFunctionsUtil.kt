package com.example.bbsuestc.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import android.widget.Toast
import java.lang.Exception
import java.lang.reflect.Field

fun ViewPager2.adjustScrollSensitivity(vp: ViewPager2, factor: Int) {
    //通过反射动态设置ViewPager2 灵敏度
    try {
        val recyclerViewField: Field = ViewPager2::class.java.getDeclaredField("mRecyclerView")
        recyclerViewField.isAccessible = true
        val recyclerView = recyclerViewField.get(vp) as RecyclerView
        val touchSlopField: Field = RecyclerView::class.java.getDeclaredField("mTouchSlop")
        touchSlopField.isAccessible = true
        val touchSlop = touchSlopField.get(recyclerView) as Int
        touchSlopField.set(recyclerView, touchSlop * factor)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun RecyclerView.fixHeight() {
    //固定rv高度，以解决在scrollView下的rv会直接全部绘制的问题
    // TODO: 这么写有点问题，不是所有的rv都能无脑call这个函数，maybe fixable？
    //在具体使用的时候具体判断
    val params = this.layoutParams
    params.height = resources.displayMetrics.heightPixels
    this.layoutParams = params
}

