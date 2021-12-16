package com.pss.djmw_android.view.main.question

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseFragment
import com.pss.djmw_android.databinding.FragmentQuestionBinding
import com.pss.djmw_android.view.main.question.adapter.QuestionViewPagerAdapter
import com.pss.djmw_android.viewmodel.MainViewModel
import com.pss.djmw_android.widget.extension.setVisibility
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuestionFragment : BaseFragment<FragmentQuestionBinding>(R.layout.fragment_question) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        if (mainViewModel.eventGetUserInfo.value?.sex == null){
            binding.frame.setVisibility(false)
            binding.loadingBar.setVisibility(true)
            CoroutineScope(Dispatchers.Main).launch {
                delay(1000)
                initTxt()
                initTypeWriterTextAnim()
                initViewPager()
                initTabLayout()
                binding.frame.setVisibility(true)
                binding.loadingBar.setVisibility(false)
            }
        }else{
            initTxt()
            initTypeWriterTextAnim()
            initViewPager()
            initTabLayout()
        }
    }

    private fun initTxt(){
        Log.d("로그","성별 : ${mainViewModel.eventGetUserInfo.value?.userName}")
        when(mainViewModel.eventGetUserInfo.value?.sex){
            "man" ->{
                binding.sex.text = "남자"
                binding.sex.setTextColor(ContextCompat.getColor(requireContext(), R.color.man_txt_color))
            }
            "woman" ->{
                binding.sex.text = "여자"
                binding.sex.setTextColor(ContextCompat.getColor(requireContext(), R.color.woman_txt_color))
            }
        }
    }

    private fun initViewPager(){
        val adapter = QuestionViewPagerAdapter(childFragmentManager, lifecycle)
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