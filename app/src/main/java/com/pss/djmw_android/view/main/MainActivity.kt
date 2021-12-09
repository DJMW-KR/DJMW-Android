package com.pss.djmw_android.view.main

import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseActivity
import com.pss.djmw_android.data.model.Question
import com.pss.djmw_android.data.model.UserInfo
import com.pss.djmw_android.databinding.ActivityMainBinding
import com.pss.djmw_android.viewmodel.MainViewModel
import com.pss.djmw_android.viewmodel.PostViewModel
import com.pss.djmw_android.widget.extension.setVisibility
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel by viewModels<MainViewModel>()
    private val postViewModel by viewModels<PostViewModel>()


    override fun init() {
        observeViewModel()
        initGetValues()
    }

    private fun initGetValues() {
        mainViewModel.userRankingList =
            intent.getParcelableArrayListExtra<UserInfo>("userRankingList") as ArrayList<UserInfo>
        mainViewModel.questionList =
            intent.getParcelableArrayListExtra<Question>("questionList") as ArrayList<Question>
        intent.getParcelableExtra<UserInfo>("userInfo")
            ?.let { mainViewModel.setEventGetUserInfo(it) }
        mainViewModel.setUserRankingInfo(intent.getIntExtra("userRanking", 9999))
    }

    private fun observeViewModel() {
        mainViewModel.eventActionView.observe(this, {
            when (it) {
                true -> binding.bottomNav.setVisibility(true)
                false -> binding.bottomNav.setVisibility(false)
            }
        })

        mainViewModel.eventUserRankingInfo.observe(this,{
            initBottomNavBar()
        })
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