package com.pss.djmw_android.view.main.question.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pss.djmw_android.R
import com.pss.djmw_android.databinding.FragmentOrderBottomDialogBinding
import com.pss.djmw_android.viewmodel.MainViewModel

class OrderBottomDialogFragment(val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment() {
    private val viewModel by activityViewModels<MainViewModel>()


    private lateinit var binding: FragmentOrderBottomDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_order_bottom_dialog,
            container,
            false
        )
        binding.fragment = this
        return binding.root
    }

    fun onClickEvent(view: View) {
        when (view.id) {
            binding.question1.id -> itemClick(0)
            binding.question2.id -> itemClick(1)
            binding.question3.id -> itemClick(2)
            binding.question4.id -> itemClick(3)
            binding.question5.id -> itemClick(4)
        }
        dialog?.dismiss()
    }

    override fun onResume() {
        super.onResume()
        initText()
    }

    private fun initText() {
        val holder = viewModel.questionItemPosition
        try {
            binding.question1.text = viewModel.questionList[holder].answer_one
            binding.question2.text = viewModel.questionList[holder].answer_two
            binding.question3.text = viewModel.questionList[holder].answer_three
            binding.question4.text = viewModel.questionList[holder].answer_four
            binding.question5.text = viewModel.questionList[holder].answer_five

        } catch (e: Exception) {
            Toast.makeText(requireContext(), "질문을 받아오는데 오류가 발생했습니다", Toast.LENGTH_SHORT).show()
        }
    }
}