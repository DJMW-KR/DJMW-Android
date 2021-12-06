package com.pss.djmw_android.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val userName : String,
    val userId : String,
    //정답 문제
    val answerQuestion : String,
    //참여한 질문
    val participationQuestion : String
) : Parcelable {
    constructor() : this("오류","오류","0","0")
}
