package com.pss.djmw_android.view.main

import androidx.fragment.app.activityViewModels
import com.pss.djmw_android.base.BaseFragment
import com.pss.djmw_android.databinding.FragmentProfileBinding
import com.pss.djmw_android.viewmodel.MainViewModel

import android.animation.ValueAnimator
import android.content.Intent
import android.net.Uri
import android.view.View

import android.widget.TextView
import com.pss.djmw_android.R


class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        binding.fragment = this
        initText()
    }

    fun clickAppGuideBtn(view: View) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://dolomite-sceptre-401.notion.site/296fcd6b77984184973451b682fa27ae")
            )
        )
    }

    private fun initText() {
        binding.apply {
            mainViewModel.eventGetUserInfo.value?.apply {
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

                //랭킹 등수 카운트
                userInfoCountAnimation(
                    mainViewModel.eventUserRankingInfo.value.toString().toInt(),
                    binding.rankingScore
                )
                userNiceName.text = userName
            }
        }
    }

    private fun userInfoCountAnimation(count: Int, txtView: TextView) {
        val animator = ValueAnimator.ofInt(0, count)
        animator.duration = 1500
        animator.addUpdateListener { animation ->
            txtView.text = animation.animatedValue.toString()
        }
        animator.start()
    }
}