package com.pss.djmw_android.view.main

import android.graphics.Color
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseActivity
import com.pss.djmw_android.data.model.Question
import com.pss.djmw_android.data.model.UserInfo
import com.pss.djmw_android.data.model.UserParticipationInfo
import com.pss.djmw_android.databinding.ActivityMainBinding
import com.pss.djmw_android.viewmodel.MainViewModel
import com.pss.djmw_android.viewmodel.PostViewModel
import com.pss.djmw_android.widget.extension.setStatusBarColor
import com.pss.djmw_android.widget.extension.setVisibility
import com.pss.djmw_android.widget.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()
    private val postViewModel by viewModels<PostViewModel>()


    override fun init() {
        this.setStatusBarColor(Color.parseColor("#FAFAFA"))
        observeViewModel()
        initGetValues()
    }

    private fun initGetValues() {
        with(mainViewModel) {
            userRankingList =
                intent.getParcelableArrayListExtra<UserInfo>("userRankingList") as ArrayList<UserInfo>
            manQuestionList =
                intent.getParcelableArrayListExtra<Question>("manQuestionList") as ArrayList<Question>
            setUserRankingInfo(intent.getIntExtra("userRanking", 9999))
            womanQuestionList =
                intent.getParcelableArrayListExtra<Question>("womanQuestionList") as ArrayList<Question>
            setEventUserParticipationInfo(intent.getParcelableExtra<UserParticipationInfo>("userParticipation")!!)
        }
    }

    private fun observeViewModel() {
        mainViewModel.eventActionView.observe(this) {
            when (it) {
                true -> binding.bottomNav.setVisibility(true)
                false -> binding.bottomNav.setVisibility(false)
            }
        }

        mainViewModel.eventUserParticipationInfo.observe(this) {
            Log.d("로그", "user참여 통계 정보 : $it")
        }

        mainViewModel.eventGetUserInfo.observe(this) {
            Log.d("로그", "값 저장됨 : $it")
            initBottomNavBar()
        }

        mainViewModel.eventUserRankingInfo.observe(this) {
            intent.getParcelableExtra<UserInfo>("userInfo")
                ?.let { mainViewModel.setEventGetUserInfo(it) }
        }
    }

    private fun initBottomNavBar() {
        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host)?.findNavController()
        val nav = binding.bottomNav as BottomNavigationView
        navController?.let {
            nav.setupWithNavController(navController)
        }
    }
}