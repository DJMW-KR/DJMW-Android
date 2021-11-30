package com.pss.djmw_android.view.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseFragment
import com.pss.djmw_android.databinding.FragmentProfileBinding
import com.pss.djmw_android.viewmodel.MainViewModel


class ProfileFragment : BaseFragment<FragmentProfileBinding>(R.layout.fragment_profile) {
    private val viewModel by activityViewModels<MainViewModel>()


    override fun init() {
        initText()
    }

    private fun initText() {
        binding.apply {
            viewModel.eventGetUserInfo.value?.apply {
                answerQuestionScore.text = answerQuestion
                userNiceName.text = userName
                //rankingScore.text =
                participationQuestionScore.text = participationQuestion
            }
        }
    }
}