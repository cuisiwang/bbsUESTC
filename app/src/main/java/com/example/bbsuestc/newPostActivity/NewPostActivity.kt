package com.example.bbsuestc.newPostActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.bbsuestc.R
import org.w3c.dom.Text

class NewPostActivity : AppCompatActivity() {

    private lateinit var back : ImageView
    private lateinit var post : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_post)

        back = findViewById(R.id.new_post_toolbar_back_iv)
        post = findViewById(R.id.new_post_toolbar_post_tv)

        back.setOnClickListener { finish() }
        post.setOnClickListener { doPost() }

    }

    private fun doPost() {

    }
}