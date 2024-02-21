package com.example.bbsuestc.newPostActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.am.widget.smoothinputlayout.SmoothInputLayout
import com.example.bbsuestc.R
import com.example.bbsuestc.widget.RichContentEdittext

class NewPostActivity : AppCompatActivity() {

    private lateinit var smoothLayout : SmoothInputLayout
    private lateinit var backIv : ImageView
    private lateinit var postTv : TextView
    private lateinit var titleEt : EditText
    private lateinit var contentRichEt : RichContentEdittext
    private lateinit var emojiIv : ImageView
    private lateinit var photoIv : ImageView
    private lateinit var atIv : ImageView
    private lateinit var attachmentIv : ImageView
    private lateinit var voteIV : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        backIv = findViewById(R.id.new_post_toolbar_back_iv)
        postTv = findViewById(R.id.new_post_toolbar_post_tv)
        smoothLayout = findViewById(R.id.new_post_whole_smooth_layout)

        backIv.setOnClickListener { finish() }
        postTv.setOnClickListener { doPost() }

    }

    private fun doPost() {
        //模拟
        smoothLayout.showInputPane(true)
    }
}