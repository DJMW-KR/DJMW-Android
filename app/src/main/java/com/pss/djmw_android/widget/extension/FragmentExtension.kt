package com.pss.djmw_android.widget.extension

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.showVertical(context: Context){
    this.layoutManager =
        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
}

//RecyclerView Horizontal
fun RecyclerView.showHorizontal(context: Context){
    this.layoutManager =
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
}

fun TextView.gone(){
    this.visibility = View.GONE
}

fun TextView.visible(){
    this.visibility = View.VISIBLE
}

fun ImageView.gone(){
    this.visibility = View.GONE
}

fun ImageView.visible(){
    this.visibility = View.VISIBLE
}