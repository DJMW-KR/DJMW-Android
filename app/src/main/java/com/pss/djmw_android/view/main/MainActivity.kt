package com.pss.djmw_android.view.main

import androidx.activity.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseActivity
import com.pss.djmw_android.data.model.Question
import com.pss.djmw_android.databinding.ActivityMainBinding
import com.pss.djmw_android.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()


    override fun init() {
        viewModel.questionList = intent.getParcelableArrayListExtra<Question>("questionList") as ArrayList<Question>
        initBottomNavBar()
    }

    private fun initBottomNavBar(){
        val navController = supportFragmentManager.findFragmentById(R.id.nav_host)?.findNavController()
        val nav = binding.bottomNav as BottomNavigationView
        navController?.let {
            nav.setupWithNavController(navController)
        }
    }
}