package com.pss.djmw_android.view.main.question

import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseFragment
import com.pss.djmw_android.databinding.FragmentAnswerBinding
import com.pss.djmw_android.view.main.question.adapter.AnswerRecyclerViewAdapter
import com.pss.djmw_android.widget.extension.showVertical

class AnswerFragment : BaseFragment<FragmentAnswerBinding>(R.layout.fragment_answer) {
    override fun init() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.answerRecyclerView.showVertical(requireContext())
        binding.answerRecyclerView.adapter = AnswerRecyclerViewAdapter()
    }
}