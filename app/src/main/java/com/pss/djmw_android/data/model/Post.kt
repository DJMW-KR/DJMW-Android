package com.pss.djmw_android.data.model

data class Post(
    val title : String,
    val content : String,
    val date : String
){
    constructor() : this("오류가 발생했습니다","오류가 발생했습니다","오류가 발생했습니다")
}
