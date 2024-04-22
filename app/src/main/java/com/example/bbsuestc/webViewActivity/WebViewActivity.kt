package com.example.bbsuestc.webViewActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.bbsuestc.R

class WebViewActivity : AppCompatActivity() {
    private lateinit var wv : WebView
    private val bundle by lazy { intent.extras }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        wv = findViewById(R.id.web_view_activity_wv)

        wv.webViewClient = WebViewClient()
        wv.loadUrl(bundle?.getString("url","https://bbs.uestc.edu.cn")!!)
    }
}