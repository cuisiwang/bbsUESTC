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

    private var logoCenterY: Float = 0.0F
    private lateinit var logoLayout: LinearLayout
    private lateinit var loginLayout: LinearLayout
    private lateinit var autoLoginBt: RadioButton
    private lateinit var loginBt: Button
    private lateinit var forgetPassword: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)

        var isAutoLogin = false
        logoLayout = findViewById(R.id.login_linearlayout_with_logo)
        loginLayout = findViewById(R.id.login_linearlayout)
        autoLoginBt = findViewById(R.id.auto_login_rb)
        loginBt = findViewById(R.id.login_bt)
        forgetPassword = findViewById(R.id.find_password_tv)  //忘记密码，注册

        logoLayout.post {
            initializeLogo()
        }

        logoLayout.postDelayed({ loginAnimation() }, 2000)

        autoLoginBt.setOnClickListener {
            if (isAutoLogin) {
                autoLoginBt.isChecked = false
                isAutoLogin = false
            } else {
                autoLoginBt.isChecked = true
                isAutoLogin = true
            }
        }

        loginBt.setOnClickListener { login() }

        forgetPassword.setOnClickListener { startForgetPasswordActivity() }

    }

    private fun initializeLogo() {
        val screenHeight: Int = resources.displayMetrics.heightPixels
        val logo: ImageView = findViewById(R.id.start_logo_iv)
        val logoHeight = logo.height
        val header: View = findViewById(R.id.login_header_view)
        val headerHeight = header.height

        logoCenterY = (screenHeight - logoHeight) / 2f - headerHeight
        val moveDownAnimation: ObjectAnimator =
            ObjectAnimator.ofFloat(logoLayout, "translationY", 0f, logoCenterY)
        moveDownAnimation.duration = 0
        moveDownAnimation.start()
    }

    private fun loginAnimation() {
        val moveUpAnimation: ObjectAnimator =
            ObjectAnimator.ofFloat(logoLayout, "translationY", logoCenterY, 0F)
        val loginLayoutVisible: ObjectAnimator =
            ObjectAnimator.ofFloat(loginLayout, "alpha", 0f, 1f)
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

    private fun startForgetPasswordActivity() {
        val forgetPasswordDialog: BottomSheetDialog = BottomSheetDialog(this)
        val bottomSheetView: View =
            layoutInflater.inflate(R.layout.dialog_home_find_password, null)
        forgetPasswordDialog.setContentView(bottomSheetView)

        val forgetPasswordTV: TextView =
            bottomSheetView.findViewById(R.id.dialog_forget_password_tv)
        val signupTV: TextView = bottomSheetView.findViewById(R.id.dialog_signup_tv)
        val cancelTV: TextView = bottomSheetView.findViewById(R.id.cancelTv)

        forgetPasswordTV.setOnClickListener {
            val intent = Intent(this, ForgetPasswordActivity::class.java)
            startActivity(intent)
            forgetPasswordDialog.dismiss()
        }

        signupTV.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
            forgetPasswordDialog.dismiss()
        }

        cancelTV.setOnClickListener {
            forgetPasswordDialog.dismiss()
        }

        forgetPasswordDialog.show()
    }
}