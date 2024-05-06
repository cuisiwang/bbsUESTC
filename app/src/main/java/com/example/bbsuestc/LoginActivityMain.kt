package com.example.bbsuestc

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bbsuestc.homeActivity.HomeActivity
import com.example.bbsuestc.utils.APIStatics
import com.example.bbsuestc.utils.LoginResponse
import com.example.bbsuestc.utils.TokenManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.textfield.TextInputEditText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.math.log


class LoginActivityMain : AppCompatActivity() {

    private var logoCenterY: Float = 0.0F
    private lateinit var logoLayout: LinearLayout
    private lateinit var loginLayout: LinearLayout
    private lateinit var autoLoginBt: RadioButton
    private lateinit var loginBt: Button
    private lateinit var forgetPassword: TextView
    private lateinit var loginName:TextInputEditText
    private lateinit var loginPwd:TextInputEditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_main)

        var isAutoLogin = false
        logoLayout = findViewById(R.id.login_linearlayout_with_logo)
        loginLayout = findViewById(R.id.login_linearlayout)
        autoLoginBt = findViewById(R.id.auto_login_rb)
        loginBt = findViewById(R.id.login_bt)
        forgetPassword = findViewById(R.id.find_password_tv)  //忘记密码，注册
        loginName = findViewById(R.id.login_username_tf)
        loginPwd = findViewById(R.id.login_password_tf)

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
        val name = loginName.text.toString()
        val password = loginPwd.text.toString()
        val authService = APIStatics.createService(APIStatics.ServiceInterface::class.java)
        val call = authService.login(type = "login", username = name, password = password)
        //展示登录动画
        val dialog = layoutInflater.inflate(R.layout.progress_indicator_full_screen,null)
        dialog.visibility=View.VISIBLE
        addContentView(dialog, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT))
        call.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()!!
                    // 处理登录成功后的响应
                    dialog.visibility = View.GONE
                    TokenManager.saveTokenAndSecret(loginResponse.token,loginResponse.secret)//存储token和secret
                    Toast.makeText(this@LoginActivityMain, "登录成功！", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivityMain, HomeActivity::class.java)//跳转至主页
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this@LoginActivityMain, "登录失败！请确认用户账号与密码！", Toast.LENGTH_SHORT).show()
                    dialog.visibility = View.GONE
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Toast.makeText(this@LoginActivityMain, "请求失败！请检查是否处于校内网络环境！", Toast.LENGTH_SHORT).show()
                dialog.visibility = View.GONE

            }
        })


//        val dialog = layoutInflater.inflate(R.layout.progress_indicator_full_screen,null)
//        dialog.visibility=View.VISIBLE
//        addContentView(dialog, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
//        dialog.postDelayed({
//            dialog.visibility = View.GONE
//            val intent = Intent(this, HomeActivity::class.java)
//            startActivity(intent)
//            finish()
//        },3521)
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