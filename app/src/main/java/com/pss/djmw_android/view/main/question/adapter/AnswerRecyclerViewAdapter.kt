package com.pss.djmw_android.view.main.question.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pss.djmw_android.R
import com.pss.djmw_android.data.model.Question
import com.pss.djmw_android.databinding.QuestionRecyclerViewItemBinding
import com.pss.djmw_android.viewmodel.MainViewModel

class AnswerRecyclerViewAdapter (
    private val viewModel: MainViewModel
) : RecyclerView.Adapter<AnswerRecyclerViewAdapter.AnswerRecyclerViewHolder>() {

    init {

    }

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
       holder.bind(viewModel.questionList[position])
    }

    override fun getItemCount(): Int {
//        return viewModel.eventGetQuestion.value!!.size()
        return viewModel.questionList.size
    }

    class AnswerRecyclerViewHolder(val binding: QuestionRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Question) {
            binding.data = data
            binding.executePendingBindings()
        }
    }
}