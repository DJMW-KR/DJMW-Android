package com.pss.djmw_android.view.main.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseFragment
import com.pss.djmw_android.databinding.FragmentPostBinding
import com.pss.djmw_android.view.main.post.adapter.PostRecyclerViewAdapter
import com.pss.djmw_android.view.main.question.adapter.AnswerRecyclerViewAdapter
import com.pss.djmw_android.viewmodel.PostViewModel
import com.pss.djmw_android.widget.extension.showVertical

class PostFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {
    private val postViewModel by activityViewModels<PostViewModel>()


    override fun init() {
        initRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel(){
        postViewModel.eventGetPostResponse.observe(this,{
            Log.d("로그","Post 가져온 값 : $it")
        })
    }

    private fun initRecyclerView() {
        binding.postRecyclerView.showVertical(requireContext())
        binding.postRecyclerView.adapter = PostRecyclerViewAdapter()
    }
}