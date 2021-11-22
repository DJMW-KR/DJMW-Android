package com.pss.djmw_android.view.main.question

import android.util.Log
import androidx.fragment.app.activityViewModels
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseFragment
import com.pss.djmw_android.databinding.FragmentAnswerBinding
import com.pss.djmw_android.view.main.question.adapter.AnswerRecyclerViewAdapter
import com.pss.djmw_android.viewmodel.MainViewModel
import com.pss.djmw_android.widget.extension.showVertical

// 대답하기
class AnswerFragment : BaseFragment<FragmentAnswerBinding>(R.layout.fragment_answer) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        Log.d("TAG","AnswerFragment initRecyclerView 메서드 호출 : ${mainViewModel.questionList.size}")
        binding.answerRecyclerView.showVertical(requireContext())
        binding.answerRecyclerView.adapter = AnswerRecyclerViewAdapter(mainViewModel)
    }
}