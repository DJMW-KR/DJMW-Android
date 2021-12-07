package com.pss.djmw_android.widget.extension

import android.view.View
import android.widget.ImageView
import android.widget.TextView

//true = visible , false = gone
fun View.setVisibility(v: Boolean) {
    this.visibility = if (v) View.VISIBLE else View.GONE
}