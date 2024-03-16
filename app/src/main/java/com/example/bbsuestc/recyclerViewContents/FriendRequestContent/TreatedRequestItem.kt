package com.example.bbsuestc.recyclerViewContents.FriendRequestContent

data class TreatedRequestItem (
    val treatedRequestUserIcon:String,
    val treatedRequestUserName:String,
    //false代表已拒绝，true代表已同意
    val optionChosen:Boolean
)