package com.pss.djmw_android.widget.extension

import android.view.View
import android.widget.ImageView
import android.widget.TextView


fun TextView.setGone(){
    this.visibility = View.GONE
}

fun TextView.setVisible(){
    this.visibility = View.VISIBLE
}

fun ImageView.setGone(){
    this.visibility = View.GONE
}

fun ImageView.setVisible(){
    this.visibility = View.VISIBLE
}