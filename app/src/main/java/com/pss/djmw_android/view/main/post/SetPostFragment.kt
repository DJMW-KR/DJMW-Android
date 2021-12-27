package com.pss.djmw_android.view.main.post

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
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
import com.pss.djmw_android.data.model.Post
import com.pss.djmw_android.databinding.FragmentSetPostBinding
import com.pss.djmw_android.viewmodel.MainViewModel
import com.pss.djmw_android.viewmodel.PostViewModel
import com.pss.djmw_android.widget.extension.setVisibility
import java.text.SimpleDateFormat
import java.util.*


class SetPostFragment : BaseFragment<FragmentSetPostBinding>(R.layout.fragment_set_post) {
    private val mainViewModel by activityViewModels<MainViewModel>()
    private val postViewModel by activityViewModels<PostViewModel>()
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
        observeViewModel()
    }

    private fun observeViewModel(){
        postViewModel.eventError.observe(this,{
            binding.uploadBtn.isEnabled = true
            binding.loadingBar.setVisibility(false)
            when(it){
                1 -> shortShowToast("고민글 업로드에 실패했습니다")
            }
        })

        postViewModel.eventSetPostSuccess.observe(this,{
            mainViewModel.setActionView(true)
            binding.uploadBtn.isEnabled = true
            binding.loadingBar.setVisibility(false)
            this.findNavController().popBackStack()
        })
    }

    fun clickBackBtn(view: View){
        mainViewModel.setActionView(true)
        this.findNavController().popBackStack()
    }

    fun clickUploadBtn(view: View){
        if (!TextUtils.isEmpty(binding.title.text.toString())&&!TextUtils.isEmpty(binding.content.text.toString())){
            binding.uploadBtn.isEnabled = false
            binding.loadingBar.setVisibility(true)
            postViewModel.setPost(Post(binding.title.text.toString(), binding.content.text.toString(), nowTime()))
        }else shortShowToast("제목 또는 내용을 입력해 주세요")
    }

    private fun nowTime(): String = SimpleDateFormat("yyyy.MM.dd HH시 mm분", Locale("ko", "KR")).format(Date(System.currentTimeMillis()))
}