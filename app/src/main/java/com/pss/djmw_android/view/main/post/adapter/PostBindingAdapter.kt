package com.pss.djmw_android.view.main.post.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

object PostBindingAdapter {

    @JvmStatic
    @BindingAdapter("set_title")
    fun setTitle(text: TextView, title: String) {
        text.text = title
    }

    @JvmStatic
    @BindingAdapter("set_content")
    fun setContent(text: TextView, content: String) {
        text.text = content
    }

    @JvmStatic
    @BindingAdapter("set_date")
    fun setDate(text: TextView, date: String) {
        text.text = date
    }
}