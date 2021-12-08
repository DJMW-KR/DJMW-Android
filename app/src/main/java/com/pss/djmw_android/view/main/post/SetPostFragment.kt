package com.pss.djmw_android.view.main.post

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseFragment
import com.pss.djmw_android.databinding.FragmentSetPostBinding
import com.pss.djmw_android.viewmodel.MainViewModel


class SetPostFragment : BaseFragment<FragmentSetPostBinding>(R.layout.fragment_set_post) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private lateinit var callback: OnBackPressedCallback


/*    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                Log.d("로그","setPost 뒤로가기 버튼 눌림")
                mainViewModel.setActionView(true)
                findNavController().navigate(R.id.action_setPostFragment_to_postFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }*/

    override fun init() {
        binding.fragment = this

    }

    fun clickBackBtn(view: View){
        mainViewModel.setActionView(true)
        this.findNavController().popBackStack()
    }
}