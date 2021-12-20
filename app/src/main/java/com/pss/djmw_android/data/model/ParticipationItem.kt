package com.pss.djmw_android.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class ParticipationItem(
    val q1 : Boolean,
    val q2 : Boolean,
    val q3 : Boolean,
    val q4 : Boolean,
    val q5 : Boolean
) : Parcelable {
    constructor() : this(true,true,true,true,true)
}