package com.pss.djmw_android.widget.extension

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pss.djmw_android.widget.utils.LinearLayoutManagerWrapper

//true = visible , false = gone
fun View.setVisibility(v: Boolean) {
    this.visibility = if (v) View.VISIBLE else View.GONE
}

fun RecyclerView.showVertical(context: Context) {
    val linearLayoutManagerWrapepr =
        LinearLayoutManagerWrapper(context, LinearLayoutManager.VERTICAL, false)
    this.layoutManager = linearLayoutManagerWrapepr
}

//RecyclerView Horizontal
fun RecyclerView.showHorizontal(context: Context) {
    val linearLayoutManagerWrapepr =
        LinearLayoutManagerWrapper(context, LinearLayoutManager.VERTICAL, false)
    this.layoutManager = linearLayoutManagerWrapepr
}