package com.example.bbsuestc.webViewActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.bbsuestc.R

class WebViewActivity : AppCompatActivity() {
    private lateinit var wv : WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        wv = findViewById(R.id.web_view_activity_wv)

        // TODO: 打开对应网页
        wv.webViewClient = WebViewClient()
        wv.loadUrl("https://bbs.uestc.edu.cn")
    }
}