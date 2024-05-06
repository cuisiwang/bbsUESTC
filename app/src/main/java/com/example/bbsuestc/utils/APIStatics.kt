package com.example.bbsuestc.utils

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.math.BigInteger

import java.security.MessageDigest

import java.security.NoSuchAlgorithmException

object APIStatics {
    private const val BASE_URL = "http://bbs.uestc.edu.cn/mobcent/app/web/index.php?r="

    const val LOGIN = BASE_URL+"user/login" //登录
    const val REPORT = BASE_URL+"user/report"  //举报
    const val POST_LIST = BASE_URL+"forum/forumlist" //帖子列表
    const val POST_REPLY = BASE_URL+"forum/topicadmin" //发帖/回复
    const val SEARCH = BASE_URL+"forum/search"  //搜索

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(TokenInterceptor(TokenManager.getToken()!!,TokenManager.getSecret()!!))
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

    //该方法会拦截每个网络请求，并向其中添加公共参数
    private class TokenInterceptor(private val token: String, private val secret: String) :
        Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalRequest = chain.request()
            val newRequest = originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .addHeader("X-Secret", secret)
                .build()
            return chain.proceed(newRequest)
        }
    }

    interface ServiceInterface {
        @FormUrlEncoded
        @POST(LOGIN)
        fun login(
            @Field("type") type: String,
            @Field("username") username: String,
            @Field("password") password: String,
        ): Call<LoginResponse>
    }


    @Throws(NoSuchAlgorithmException::class)
    fun getAppHashValue(): String? {
        val timeString = System.currentTimeMillis().toString()
        val authkey = "appbyme_key"
        val authString = timeString.substring(0, 5) + authkey
        val md = MessageDigest.getInstance("MD5")
        val hashkey = md.digest(authString.toByteArray())
        return BigInteger(1, hashkey).toString(16).substring(8, 16) //16进制转换字符串
    }
    const val PERMISSIONS_READ_EXTERNAL_STORAGE = 10
    const val PERMISSIONS_READ_MEDIA_IMAGES = 11
    var flag = false
    val TOKEN = "a"
}