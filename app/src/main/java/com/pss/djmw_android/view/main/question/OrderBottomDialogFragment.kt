package com.pss.djmw_android.view.main.question

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
import com.pss.djmw_android.view.main.question.adapter.AnswerRecyclerViewAdapter
import com.pss.djmw_android.view.main.question.adapter.Sex
import com.pss.djmw_android.view.main.question.adapter.State
import com.pss.djmw_android.viewmodel.MainViewModel
import com.pss.djmw_android.widget.extension.setVisibility

class OrderBottomDialogFragment(
    val itemClick: (Int) -> Unit,
    val state: State,
    val sex: Sex,
    val position: Int,
    val holder: AnswerRecyclerViewAdapter.AnswerRecyclerViewHolder
) :
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
            //Answer ??????
            if (state == State.ANSWER) {
                when (viewModel.eventGetUserInfo.value?.sex) {
                    "man" -> {
                        Log.d("TAG","in side : ${viewModel.manQuestionList[holder].question}, $num, ${viewModel.eventGetUserInfo.value!!.sex}")
                        viewModel.getQuestionStatistics(
                            viewModel.manQuestionList[holder].question,
                            num,
                            viewModel.eventGetUserInfo.value!!.sex
                        )
                            .addOnSuccessListener {
                                Log.d("TAG","values = ${it.value}, $it")
                                //????????? value ??? null ?????? ?????? => ????????? rtdb ??? fireStore ??? ??? ??????????????? ??????
                                if (it.value != null) {

                                    //????????? ????????? ????????? +1?????? ?????? ??????
                                    val plusResult = it.value.toString().toInt() + 1
                                    viewModel.setQuestionStatistics(
                                        viewModel.manQuestionList[holder].question,
                                        num,
                                        plusResult,
                                        viewModel.eventGetUserInfo.value!!.sex
                                    )
                                        .addOnSuccessListener {
                                            viewModel.modifyUserQuestionParticipation(position = position, mode = "question",uid = viewModel.eventGetUserInfo.value!!.userId)
                                                .addOnSuccessListener {
                                                    success()
                                                }
                                                .addOnFailureListener {
                                                    Log.d("TAG","error1")
                                                    error()
                                                }
                                        }
                                        .addOnFailureListener {
                                            Log.d("TAG","error2")

                                            error()
                                        }

                                } else {
                                    Log.d("TAG","error3")

                                    error()
                                }
                            }
                            .addOnFailureListener {
                                Log.d("TAG","error4")
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

                                //????????? value ??? null ?????? ?????? => ????????? rtdb ??? fireStore ??? ??? ??????????????? ??????
                                if (it.value != null) {

                                    //????????? ????????? ????????? +1?????? ?????? ??????
                                    val plusResult = it.value.toString().toInt() + 1
                                    viewModel.setQuestionStatistics(
                                        viewModel.womanQuestionList[holder].question,
                                        num,
                                        plusResult,
                                        viewModel.eventGetUserInfo.value!!.sex
                                    )
                                        .addOnSuccessListener {
                                            viewModel.modifyUserQuestionParticipation(position = position, mode = "question",uid = viewModel.eventGetUserInfo.value!!.userId)
                                                .addOnSuccessListener {
                                                    success()
                                                }
                                                .addOnFailureListener {
                                                    error()
                                                }
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
            // AnswerMe ??????
            else {
                when (viewModel.eventGetUserInfo.value?.sex) {
                    "man" -> {
                        viewModel.getAnswerStatistics(
                            viewModel.manQuestionList[holder].question,
                            num,
                            viewModel.eventGetUserInfo.value!!.sex
                        )
                            .addOnSuccessListener {

                                //????????? value ??? null ?????? ?????? => ????????? rtdb ??? fireStore ??? ??? ??????????????? ??????
                                if (it.value != null) {

                                    //????????? ????????? ????????? +1?????? ?????? ??????
                                    val plusResult = it.value.toString().toInt() + 1
                                    viewModel.setAnswerStatistics(
                                        viewModel.manQuestionList[holder].question,
                                        num,
                                        plusResult,
                                        viewModel.eventGetUserInfo.value!!.sex
                                    )
                                        .addOnSuccessListener {
                                            viewModel.modifyUserQuestionParticipation(position = position, mode = "answer",uid = viewModel.eventGetUserInfo.value!!.userId)
                                                .addOnSuccessListener {
                                                    success()
                                                }
                                                .addOnFailureListener {
                                                    error()
                                                }
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
                        Log.d("??????", "AnswerMe ?????? woman")
                        viewModel.getAnswerStatistics(
                            viewModel.womanQuestionList[holder].question,
                            num,
                            viewModel.eventGetUserInfo.value!!.sex
                        )
                            .addOnSuccessListener {

                                //????????? value ??? null ?????? ?????? => ????????? rtdb ??? fireStore ??? ??? ??????????????? ??????
                                if (it.value != null) {

                                    //????????? ????????? ????????? +1?????? ?????? ??????
                                    val plusResult = it.value.toString().toInt() + 1
                                    viewModel.setAnswerStatistics(
                                        viewModel.womanQuestionList[holder].question,
                                        num,
                                        plusResult,
                                        viewModel.eventGetUserInfo.value!!.sex
                                    )
                                        .addOnSuccessListener {
                                            viewModel.modifyUserQuestionParticipation(position = position, mode = "answer",uid = viewModel.eventGetUserInfo.value!!.userId)
                                                .addOnSuccessListener {
                                                    success()
                                                }
                                                .addOnFailureListener {
                                                    error()
                                                }
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
        Toast.makeText(requireContext(), "????????? ?????? ????????? ??????????????????", Toast.LENGTH_SHORT).show()
        dialog?.dismiss()
    }

    private fun userParticipationTrue(holder: AnswerRecyclerViewAdapter.AnswerRecyclerViewHolder) {
        holder.binding.itemFrame.isEnabled = false
        holder.binding.participationStatusFalse.setVisibility(false)
        holder.binding.participationStatusTrue.setVisibility(true)
    }

    private fun success() {
        viewModel.questionClickEvent = false
        userParticipationTrue(holder)

        /*      if(sex == Sex.MAN){
                  if (state == State.ANSWER)
                      else
              }*/
        Toast.makeText(
            requireContext(),
            "??????????????? ?????????????????????!",
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
            Toast.makeText(requireContext(), "????????? ??????????????? ????????? ??????????????????", Toast.LENGTH_SHORT).show()
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