package com.pss.djmw_android.view.question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseFragment
import com.pss.djmw_android.databinding.FragmentAnswerMeBinding
import com.pss.djmw_android.view.question.adapter.AnswerRecyclerViewAdapter
import com.pss.djmw_android.widget.extension.showVertical


class AnswerMeFragment : BaseFragment<FragmentAnswerMeBinding>(R.layout.fragment_answer_me) {
    override fun init() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.answerMeRecyclerView.showVertical(requireContext())
        binding.answerMeRecyclerView.adapter = AnswerRecyclerViewAdapter()
    }
}