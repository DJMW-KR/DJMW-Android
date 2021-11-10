package com.pss.djmw_android.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseActivity
import com.pss.djmw_android.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    override fun init() {
        val navController = supportFragmentManager.findFragmentById(R.id.nav_host)?.findNavController()
        navController?.let {
           binding.bottomNav.setupWithNavController(navController)
        }




   /*     // 공식문서의 예제코드는 다음과 같다. 편한 걸 쓰면 된다.
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        //toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.bottomNav.setupWithNavController(navController)
*/
    }

}