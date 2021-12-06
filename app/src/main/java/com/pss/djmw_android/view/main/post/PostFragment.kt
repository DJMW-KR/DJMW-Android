package com.pss.djmw_android.view.main.post

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pss.djmw_android.R
import com.pss.djmw_android.base.BaseFragment
import com.pss.djmw_android.databinding.FragmentPostBinding
import com.pss.djmw_android.view.main.post.adapter.PostRecyclerViewAdapter
import com.pss.djmw_android.view.main.question.adapter.AnswerRecyclerViewAdapter
import com.pss.djmw_android.widget.extension.showVertical

class PostFragment : BaseFragment<FragmentPostBinding>(R.layout.fragment_post) {
    override fun init() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.postRecyclerView.showVertical(requireContext())
        binding.postRecyclerView.adapter = PostRecyclerViewAdapter()
    }
}