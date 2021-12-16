package com.pss.djmw_android.view.main.question.adapter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.pss.djmw_android.R
import com.pss.djmw_android.databinding.FragmentOrderBottomDialogBinding
import com.pss.djmw_android.viewmodel.MainViewModel

class OrderBottomDialogFragment(val itemClick: (Int) -> Unit, val state: State, val sex: Sex) :
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
        setEnabled()
        var num = 0
        when (view.id) {
            binding.question1.id -> {
                num = 0
                itemClick(0)
            }
            binding.question2.id -> {
                num = 1
                itemClick(1)
            }
            binding.question3.id -> {
                num = 2
                itemClick(2)
            }
            binding.question4.id -> {
                num = 3
                itemClick(3)
            }
            binding.question5.id -> {
                num = 4
                itemClick(4)
            }

        }
        val holder = viewModel.questionItemPosition
        try {
            //Answer 부분
            if (state == State.ANSWER){
                when (viewModel.eventGetUserInfo.value?.sex) {
                    "man" -> {
                        viewModel.getQuestionStatistics(
                            viewModel.manQuestionList[holder].question,
                            num,
                            viewModel.eventGetUserInfo.value!!.sex
                        )
                            .addOnSuccessListener {

                                //가져온 value 가 null 인지 체크 => 질문이 rtdb 와 fireStore 에 잘 들어있는지 확인
                                if (it.value != null) {

                                    //불러온 참여한 사람에 +1해서 다시 저장
                                    val plusResult = it.value.toString().toInt() + 1
                                    viewModel.setQuestionStatistics(
                                        viewModel.manQuestionList[holder].question,
                                        num,
                                        plusResult,
                                        viewModel.eventGetUserInfo.value!!.sex
                                    )
                                        .addOnSuccessListener {
                                            success()
                                        }
                                        .addOnFailureListener {
                                            error()
                                        }

                                } else {
                                    error()
                                }
                            }
                            .addOnFailureListener {
                                error()
                            }
                    }
                    "woman" -> {
                        viewModel.getQuestionStatistics(
                            viewModel.womanQuestionList[holder].question,
                            num,
                            viewModel.eventGetUserInfo.value!!.sex
                        )
                            .addOnSuccessListener {

                                //가져온 value 가 null 인지 체크 => 질문이 rtdb 와 fireStore 에 잘 들어있는지 확인
                                if (it.value != null) {

                                    //불러온 참여한 사람에 +1해서 다시 저장
                                    val plusResult = it.value.toString().toInt() + 1
                                    viewModel.setQuestionStatistics(
                                        viewModel.womanQuestionList[holder].question,
                                        num,
                                        plusResult,
                                        viewModel.eventGetUserInfo.value!!.sex
                                    )
                                        .addOnSuccessListener {
                                            success()
                                        }
                                        .addOnFailureListener {
                                            error()
                                        }

                                } else {
                                    error()
                                }
                            }
                            .addOnFailureListener {
                                error()
                            }
                    }
                }
            }
            // AnswerMe 부분
            else {
                when (viewModel.eventGetUserInfo.value?.sex) {
                    "man" -> {
                        viewModel.getAnswerStatistics(
                            viewModel.manQuestionList[holder].question,
                            num,
                            viewModel.eventGetUserInfo.value!!.sex
                        )
                            .addOnSuccessListener {

                                //가져온 value 가 null 인지 체크 => 질문이 rtdb 와 fireStore 에 잘 들어있는지 확인
                                if (it.value != null) {

                                    //불러온 참여한 사람에 +1해서 다시 저장
                                    val plusResult = it.value.toString().toInt() + 1
                                    viewModel.setAnswerStatistics(
                                        viewModel.manQuestionList[holder].question,
                                        num,
                                        plusResult,
                                        viewModel.eventGetUserInfo.value!!.sex
                                    )
                                        .addOnSuccessListener {
                                            success()
                                        }
                                        .addOnFailureListener {
                                            error()
                                        }

                                } else {
                                    error()
                                }
                            }
                            .addOnFailureListener {
                                error()
                            }
                    }
                    "woman" -> {
                        Log.d("로그","AnswerMe 안에 woman")
                        viewModel.getAnswerStatistics(
                            viewModel.womanQuestionList[holder].question,
                            num,
                            viewModel.eventGetUserInfo.value!!.sex
                        )
                            .addOnSuccessListener {

                                //가져온 value 가 null 인지 체크 => 질문이 rtdb 와 fireStore 에 잘 들어있는지 확인
                                if (it.value != null) {

                                    //불러온 참여한 사람에 +1해서 다시 저장
                                    val plusResult = it.value.toString().toInt() + 1
                                    viewModel.setAnswerStatistics(
                                        viewModel.womanQuestionList[holder].question,
                                        num,
                                        plusResult,
                                        viewModel.eventGetUserInfo.value!!.sex
                                    )
                                        .addOnSuccessListener {
                                            success()
                                        }
                                        .addOnFailureListener {
                                            error()
                                        }

                                } else {
                                    error()
                                }
                            }
                            .addOnFailureListener {
                                error()
                            }
                    }
                }
            }


        } catch (e: Exception) {
            error()
        }
    }

    private fun error() {
        viewModel.questionClickEvent = false
        Toast.makeText(requireContext(), "예기치 않은 오류가 발생했습니다", Toast.LENGTH_SHORT).show()
        dialog?.dismiss()
    }

    private fun success(){
        viewModel.questionClickEvent = false
        Toast.makeText(
            requireContext(),
            "성공적으로 반영되었습니다!",
            Toast.LENGTH_SHORT
        )
            .show()
        dialog?.dismiss()
    }

    override fun onResume() {
        super.onResume()
        initText()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.questionClickEvent = false
    }

    private fun initAnswerTxt(holder: Int, sex: Sex) {
        if (sex == Sex.MAN) with(viewModel.manQuestionList[holder]) {
            binding.question1.text = this.answer_one
            binding.question2.text = this.answer_two
            binding.question3.text = this.answer_three
            binding.question4.text = this.answer_four
            binding.question5.text = this.answer_five
        }
        else with(viewModel.womanQuestionList[holder]) {
            binding.question1.text = this.answer_one
            binding.question2.text = this.answer_two
            binding.question3.text = this.answer_three
            binding.question4.text = this.answer_four
            binding.question5.text = this.answer_five
        }
    }

    private fun initText() {
        val holder = viewModel.questionItemPosition
        try {
            when (state) {
                State.ANSWER -> when (viewModel.eventGetUserInfo.value?.sex) {
                    "man" -> {
                        initAnswerTxt(holder, Sex.MAN)
                    }
                    "woman" -> {
                        initAnswerTxt(holder, Sex.WOMAN)
                    }
                }

                State.ANSWERME -> when (viewModel.eventGetUserInfo.value?.sex) {
                    "man" -> {
                        initAnswerTxt(holder, Sex.WOMAN)
                    }
                    "woman" -> {
                        initAnswerTxt(holder, Sex.MAN)
                    }
                }
            }

        } catch (e: Exception) {
            Toast.makeText(requireContext(), "질문을 받아오는데 오류가 발생했습니다", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setEnabled() {
        binding.question1.isEnabled = false
        binding.question2.isEnabled = false
        binding.question3.isEnabled = false
        binding.question4.isEnabled = false
        binding.question5.isEnabled = false
    }
}