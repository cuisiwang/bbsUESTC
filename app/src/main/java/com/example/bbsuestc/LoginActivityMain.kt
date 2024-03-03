package com.example.bbsuestc

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bbsuestc.homeActivity.HomeActivity
import com.google.android.material.bottomsheet.BottomSheetDialog


class LoginActivityMain : AppCompatActivity() {

    var logoCenterY : Float = 0.0F
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)

        var isAutoLogin = false
        val logoLayout : LinearLayout = findViewById(R.id.login_linearlayout_with_logo)
        val loginLayout : LinearLayout = findViewById(R.id.login_linearlayout)
        val autoLoginBt : RadioButton = findViewById(R.id.auto_login_rb)
        val loginBt : Button = findViewById(R.id.login_bt)
        val forgetPassword : TextView = findViewById(R.id.find_password_tv)  //忘记密码，注册

        logoLayout.post{
            initializeLogo(logoLayout)
        }

        logoLayout.setOnClickListener { loginAnimation(logoLayout,loginLayout) }

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

    private fun initializeLogo(logoLayout: LinearLayout) {
        val screenHeight: Int = resources.displayMetrics.heightPixels
        val logo : ImageView = findViewById(R.id.start_logo_iv)
        val logoHeight = logo.height
        val header : View = findViewById(R.id.login_header_view)
        val headerHeight = header.height

        logoCenterY = (screenHeight-logoHeight)/2f - headerHeight
        val moveDownAnimation: ObjectAnimator = ObjectAnimator.ofFloat(logoLayout, "translationY", 0f, logoCenterY)
        moveDownAnimation.duration = 0
        moveDownAnimation.start()
    }

    private fun loginAnimation(logoLayout: LinearLayout, loginLayout: LinearLayout,) {
        val moveUpAnimation: ObjectAnimator = ObjectAnimator.ofFloat(logoLayout, "translationY", logoCenterY,0F)
        val loginLayoutVisible : ObjectAnimator = ObjectAnimator.ofFloat(loginLayout, "alpha",0f,1f)
        loginLayoutVisible.duration = 400
        moveUpAnimation.duration = 400
        AnimatorSet().apply {
            loginLayout.visibility = View.VISIBLE
            play(moveUpAnimation).with(loginLayoutVisible)
            start()
        }
    }

    private fun login() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun startForgetPasswordActivity(){
        val forgetPasswordDialog : BottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView: View =
            layoutInflater.inflate(R.layout.dialog_home_find_password, null)
        forgetPasswordDialog.setContentView(bottomSheetView)

        val forgetPasswordTV : TextView = bottomSheetView.findViewById(R.id.dialog_forget_password_tv)
        val signupTV : TextView = bottomSheetView.findViewById(R.id.dialog_signup_tv)
        val cancelTV : TextView = bottomSheetView.findViewById(R.id.cancelTv)

        forgetPasswordTV.setOnClickListener {
            val intent = Intent(this,ForegetPasswordActivity::class.java)
            startActivity(intent)
        }

        signupTV.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }

        cancelTV.setOnClickListener {
            forgetPasswordDialog.dismiss()
        }

        forgetPasswordDialog.show()
    }
}