package com.pss.djmw_android.view.main.question

import com.google.android.material.tabs.TabLayoutMediator
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseFragment
import com.pss.djmw_android.databinding.FragmentQuestionBinding

class QuestionFragment : BaseFragment<FragmentQuestionBinding>(R.layout.fragment_question) {
    override fun init() {
        initTypeWriterTextAnim()
        initViewPager()
        initTabLayout()
    }

    private fun initViewPager(){
        val adapter = QuestionAdapter(childFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter
    }

    private fun initTypeWriterTextAnim() {
        binding.content.apply {
            setDelay(1)
            setWithMusic(false)
            animateText("이성의 질문에 답변해 보세요!")
        }
    }

    private fun initTabLayout(){
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "대답하기"
                }
                1 -> {
                    tab.text = "선택하기"
                }
            }
        }.attach()
    }
}