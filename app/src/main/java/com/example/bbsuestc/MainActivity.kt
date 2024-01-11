package com.example.bbsuestc

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var isAutoLogin = false
        val logoLayout : LinearLayout = findViewById(R.id.login_linearlayout_with_logo)
        val loginLayout : LinearLayout = findViewById(R.id.login_linearlayout)

        initializeLogo(logoLayout,loginLayout)
        logoLayout.setOnClickListener { v -> loginAnimation(logoLayout,loginLayout) }

    }

    private fun initializeLogo(logoLayout : LinearLayout, loginLayout: LinearLayout) {
        val screenHeight: Int = resources.displayMetrics.heightPixels
        val moveDownDistance: Float = screenHeight/2f-200
        val moveUpAnimation: ObjectAnimator = ObjectAnimator.ofFloat(logoLayout, "translationY", 0f, moveDownDistance)
        moveUpAnimation.duration = 0
        val loginLayoutInvisible : ObjectAnimator = ObjectAnimator.ofFloat(loginLayout, "alpha",1f,0f)
        loginLayoutInvisible.duration = 0
        AnimatorSet().apply {
            play(moveUpAnimation).with(loginLayoutInvisible)
            start()
        }
    }

    private fun loginAnimation(logoLayout: LinearLayout, loginLayout: LinearLayout) {
        val screenHeight: Int = resources.displayMetrics.heightPixels
        val startY: Float = screenHeight/2f-200
        val endY : Float = screenHeight /8f
        val moveUpAnimation: ObjectAnimator = ObjectAnimator.ofFloat(logoLayout, "translationY", startY,endY)
        val loginLayoutVisible : ObjectAnimator = ObjectAnimator.ofFloat(loginLayout, "alpha",0f,1f)
        loginLayoutVisible.duration = 500
        moveUpAnimation.duration = 500 // 设置动画持续时间
        AnimatorSet().apply {
            loginLayout.visibility = View.VISIBLE
            play(moveUpAnimation).with(loginLayoutVisible)
            start()
        }
    }

}