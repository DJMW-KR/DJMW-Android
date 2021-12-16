package com.pss.djmw_android.view.main.question.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.pss.djmw_android.view.main.question.AnswerFragment
import com.pss.djmw_android.view.main.question.AnswerMeFragment

class QuestionViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AnswerFragment()
            1 -> AnswerMeFragment()
            else -> AnswerFragment()
        }
    }
}