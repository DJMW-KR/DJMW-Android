package com.pss.djmw_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseActivity
import com.pss.djmw_android.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun init() {
        val navController = supportFragmentManager.findFragmentById(R.id.nav_host)?.findNavController()

        val nav = binding.bottomNav as BottomNavigationView
        navController?.let {
           nav.setupWithNavController(navController)
        }

    }

}