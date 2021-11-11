package com.pss.djmw_android.view.question.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pss.djmw_android.R
import com.pss.djmw_android.databinding.QuestionRecyclerViewItemBinding

class AnswerRecyclerViewAdapter (
) : RecyclerView.Adapter<AnswerRecyclerViewAdapter.AnswerRecyclerViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnswerRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<QuestionRecyclerViewItemBinding>(
            layoutInflater,
            R.layout.question_recycler_view_item,
            parent,
            false
        )

        return AnswerRecyclerViewHolder(binding)
    }


    override fun onBindViewHolder(holder: AnswerRecyclerViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 10
    }

    class AnswerRecyclerViewHolder(val binding: QuestionRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
 /*       fun bind(data: Document) {
            binding.data = data
            binding.executePendingBindings()
        }*/
    }
}