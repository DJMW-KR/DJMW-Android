package com.pss.djmw_android.view.main

import androidx.fragment.app.activityViewModels
import com.pss.djmw_android.base.BaseFragment
import com.pss.djmw_android.databinding.FragmentProfileBinding
import com.pss.djmw_android.viewmodel.MainViewModel

import android.animation.ValueAnimator

import android.widget.TextView
import com.pss.djmw_android.R


class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val viewModel by activityViewModels<MainViewModel>()


    override fun init() {
        initText()
    }

    private fun initText() {
        binding.apply {
            viewModel.eventGetUserInfo.value?.apply {
                //참여한 문제 개수 카운트
                userInfoCountAnimation(
                    participationQuestion.toString().toInt(),
                    binding.participationQuestionScore
                )

                //정답 문제 개수 카운트
                userInfoCountAnimation(
                    answerQuestion.toString().toInt(),
                    binding.answerQuestionScore
                )
                userNiceName.text = userName
            }
        }
    }

    private fun userInfoCountAnimation(count: Int, txtView: TextView) {
        val answerQuestionScoreAnimator = ValueAnimator.ofInt(0, count)
        answerQuestionScoreAnimator.duration = 1500
        answerQuestionScoreAnimator.addUpdateListener { animation ->
            txtView.text = animation.animatedValue.toString()
        }
        answerQuestionScoreAnimator.start()
    }
}