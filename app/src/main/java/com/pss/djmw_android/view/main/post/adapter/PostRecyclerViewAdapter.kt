package com.pss.djmw_android.view.main.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pss.djmw_android.R
import com.pss.djmw_android.databinding.PostRecyclerViewItemBinding


class PostRecyclerViewAdapter(

) : RecyclerView.Adapter<PostRecyclerViewAdapter.PostRecyclerViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<PostRecyclerViewItemBinding>(
            layoutInflater,
            R.layout.post_recycler_view_item,
            parent,
            false
        )
        return PostRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostRecyclerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }

    inner class PostRecyclerViewHolder(val binding: PostRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
 /*       fun bind(data: Question) {
            binding.data = data
            binding.executePendingBindings()
        }*/
    }
}