package com.pss.djmw_android.view.signin

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseFragment
import com.pss.djmw_android.databinding.FragmentSignInBinding

class SignInFragment : BaseFragment<FragmentSignInBinding>(R.layout.fragment_sign_in) {
    override fun init() {

    }

    private fun initColor(){
        binding.kakaoLogo.setColorFilter(Color.parseColor("#1E1C01"))

    }
}