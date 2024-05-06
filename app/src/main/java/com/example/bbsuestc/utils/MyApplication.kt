package com.example.bbsuestc.utils

import android.app.Application

class MyApplication : Application() {
    companion object {
        lateinit var instance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        // 这里可以进行一些全局的初始化工作
    }
}
