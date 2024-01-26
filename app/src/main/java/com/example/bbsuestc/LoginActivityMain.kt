package com.example.bbsuestc

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomsheet.BottomSheetDialog


class LoginActivityMain : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)

        var isAutoLogin = false
        val logoLayout : LinearLayout = findViewById(R.id.login_linearlayout_with_logo)
        val loginLayout : LinearLayout = findViewById(R.id.login_linearlayout)
        val autoLoginBt : RadioButton = findViewById(R.id.auto_login_rb)
        val loginBt : Button = findViewById(R.id.login_bt)
        val forgetPassword : TextView = findViewById(R.id.find_password_tv)  //忘记密码，注册

        val originalY : Float = logoLayout.y

        initializeLogo(logoLayout)
        logoLayout.setOnClickListener { loginAnimation(logoLayout,loginLayout, originalY) }

        autoLoginBt.setOnClickListener {
            if (isAutoLogin){
                autoLoginBt.isChecked = false
                isAutoLogin = false
            }else{
                autoLoginBt.isChecked = true
                isAutoLogin = true
            }
        }

        loginBt.setOnClickListener{ login() }

        forgetPassword.setOnClickListener{ startForgetPasswordActivity() }

    }

    //关于加载动画，可以考虑动态改变顶部空view的大小，而不是凭空移动布局

    private fun initializeLogo(logoLayout: LinearLayout) {
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

    private fun login() {
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startForgetPasswordActivity(){
        val forgetPasswordDialog : BottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView: View =
            layoutInflater.inflate(R.layout.dialog_home_find_password, null)
        forgetPasswordDialog.setContentView(bottomSheetView)

        val forgetPasswordTV : TextView = bottomSheetView.findViewById(R.id.forgetPasswordTv)
        val signupTV : TextView = bottomSheetView.findViewById(R.id.signupTv)
        val cancelTV : TextView = bottomSheetView.findViewById(R.id.cancelTv)

        forgetPasswordTV.setOnClickListener {
            // TODO: 启动对应activity
        }

        signupTV.setOnClickListener {
            // TODO: 启动对应activity
        }

        cancelTV.setOnClickListener {
            forgetPasswordDialog.dismiss()
        }

        forgetPasswordDialog.show()
    }
}