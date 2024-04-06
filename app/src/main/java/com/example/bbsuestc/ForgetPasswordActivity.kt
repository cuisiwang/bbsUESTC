package com.example.bbsuestc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var back : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foreget_password)
        back = findViewById(R.id.forget_password_back_login)

        back.setOnClickListener { finish() }
    }
}