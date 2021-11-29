package com.pss.djmw_android.data.model

data class UserInfo(
    val userName : String,
    val userId : String,
    val answerQuestion : String,
    val participationQuestion : String
){
    constructor() : this("오류","오류","0","0")
}
