package com.pss.djmw_android.data.model

data class Question(
    val answer_one : String,
    val answer_two : String,
    val answer_three : String,
    val answer_four : String,
    val answer_five : String,
    val question : String,
    val people : String
) {
    constructor() : this("오류가 발생했습니다", "오류가 발생했습니다","오류가 발생했습니다","오류가 발생했습니다","오류가 발생했습니다","오류가 발생했습니다","오류가 발생했습니다",)
}