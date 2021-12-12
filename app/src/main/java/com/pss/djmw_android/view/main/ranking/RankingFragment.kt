package com.pss.djmw_android.view.main.ranking


import android.util.Log
import androidx.fragment.app.activityViewModels
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseFragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.pss.djmw_android.databinding.FragmentRankingBinding
import com.pss.djmw_android.view.main.ranking.adapter.RankingViewPagerAdapter
import com.pss.djmw_android.viewmodel.MainViewModel


class RankingFragment : BaseFragment<FragmentRankingBinding>(R.layout.fragment_ranking) {
    private val mainViewModel by activityViewModels<MainViewModel>()


    override fun init() {
        for (item in 0 until mainViewModel.userRankingList.size){
            Log.d("로그","랭킹 정보 : ${mainViewModel.userRankingList[item]}")
        }
        val imgList  = arrayListOf<Int>(R.drawable.first, R.drawable.second, R.drawable.third)
        binding.viewPager2.adapter = RankingViewPagerAdapter(mainViewModel, requireContext(),imgList)
        binding.viewPager2.setPadding(30, 0, 30, 0)

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })


    }
}