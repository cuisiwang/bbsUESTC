package com.example.bbsuestc

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.RadioButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var isAutoLogin = false
        val logoLayout : LinearLayout = findViewById(R.id.login_linearlayout_with_logo)
        val loginLayout : LinearLayout = findViewById(R.id.login_linearlayout)
        val autoLoginBt : RadioButton = findViewById(R.id.auto_login_rb)

        val originalY : Float = logoLayout.y

        initializeLogo(logoLayout,originalY)
        logoLayout.setOnClickListener { v -> loginAnimation(logoLayout,loginLayout, originalY) }

        autoLoginBt.setOnClickListener {
            if (isAutoLogin){
                autoLoginBt.isChecked = false
                isAutoLogin = false
            }else{
                autoLoginBt.isChecked = true
                isAutoLogin = true
            }
        }

    }

    private fun initializeLogo(logoLayout: LinearLayout, originalY: Float) {
        val screenHeight: Int = resources.displayMetrics.heightPixels
        val moveDownDistance: Float = screenHeight/2f-500
        val moveDownAnimation: ObjectAnimator = ObjectAnimator.ofFloat(logoLayout, "translationY", 0f, moveDownDistance)
        moveDownAnimation.duration = 0
        moveDownAnimation.start()
    }

    private fun loginAnimation(logoLayout: LinearLayout, loginLayout: LinearLayout, orignialY: Float) {
        val screenHeight: Int = resources.displayMetrics.heightPixels
        val startY: Float = screenHeight/2f-500
        val moveUpAnimation: ObjectAnimator = ObjectAnimator.ofFloat(logoLayout, "translationY", startY,orignialY)
        val loginLayoutVisible : ObjectAnimator = ObjectAnimator.ofFloat(loginLayout, "alpha",0f,1f)
        loginLayoutVisible.duration = 400
        moveUpAnimation.duration = 400 // 设置动画持续时间
        AnimatorSet().apply {
            loginLayout.visibility = View.VISIBLE
            play(moveUpAnimation).with(loginLayoutVisible)
            start()
        }
    }

}