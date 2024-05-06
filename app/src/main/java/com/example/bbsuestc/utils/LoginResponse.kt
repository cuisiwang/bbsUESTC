package com.example.bbsuestc.utils

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("secret") val secret: String,
    @SerializedName("token") val token: String,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("uid") val uid: String,
    @SerializedName("userName") val userName: String
)
