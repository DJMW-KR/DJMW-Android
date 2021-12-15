package com.pss.djmw_android.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val userName: String,
    val userId: String,
    //정답 문제
    val answerQuestion: String,
    //참여한 질문
    val participationQuestion: String,
    //점수
    val score: Int,
    //성별
    val sex: String,
    //계정 정지 유무
    val stop : Boolean
) : Parcelable {
    constructor() : this("오류", "오류", "0", "0", 0,"오류", false)
}
