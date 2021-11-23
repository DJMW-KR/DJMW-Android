package com.pss.djmw_android.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Question(
    var answer_one : String,
    var answer_two : String,
    var answer_three : String,
    var answer_four : String,
    var answer_five : String,
    var question : String,
    var people : String
) : Parcelable
{
    constructor() : this("오류가 발생했습니다", "오류가 발생했습니다","오류가 발생했습니다","오류가 발생했습니다","오류가 발생했습니다","오류가 발생했습니다","오류가 발생했습니다")
}
