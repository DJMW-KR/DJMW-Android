package com.pss.djmw_android.view.main.ranking.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter

object RankingBindingAdapter {

    //사용자 이름
    @JvmStatic
    @BindingAdapter("set_name")
    fun setName(text: TextView, name: String) {
        text.text = name
    }

    //참여질문
    @JvmStatic
    @BindingAdapter("set_question_score")
    fun setQuestionScore(text: TextView, score: String) {
        text.text = score
    }

    //점수
    @JvmStatic
    @BindingAdapter("set_score_score")
    fun setScoreScore(text: TextView, score: Int) {
        text.text = score.toString()
    }

    //질문 정답
    @JvmStatic
    @BindingAdapter("set_answer_score")
    fun setAnswerScore(text: TextView, score: String) {
        text.text = score
    }
}