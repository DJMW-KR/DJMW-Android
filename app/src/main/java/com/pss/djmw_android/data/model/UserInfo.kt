package com.pss.djmw_android.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserInfo(
    val userName : String,
    val userId : String,
    val answerQuestion : String,
    val participationQuestion : String
) : Parcelable {
    constructor() : this("오류","오류","0","0")
}
