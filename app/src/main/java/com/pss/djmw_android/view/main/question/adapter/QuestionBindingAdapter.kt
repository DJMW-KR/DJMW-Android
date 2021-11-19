package com.pss.djmw_android.view.main.question.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

object QuestionBindingAdapter {

    @JvmStatic
    @BindingAdapter("set_question")
    fun setQuestion(text: TextView, question: String) {
        text.text = question
    }

    @JvmStatic
    @BindingAdapter("set_people")
    fun setPeople(text: TextView, people: String) {
        text.text = people
    }
}