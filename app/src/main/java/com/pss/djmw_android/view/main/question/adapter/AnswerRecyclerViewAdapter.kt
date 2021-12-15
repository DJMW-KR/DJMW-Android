package com.pss.djmw_android.view.main.question.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.pss.djmw_android.R
import com.pss.djmw_android.data.model.Question
import com.pss.djmw_android.databinding.QuestionRecyclerViewItemBinding
import com.pss.djmw_android.viewmodel.MainViewModel

class AnswerRecyclerViewAdapter(
    private val viewModel: MainViewModel,
    private val fragment: Fragment
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
        Log.d("TAG", "AnswerRecyclerViewAdapter안 questionList : ${viewModel.manQuestionList}")
        holder.bind(viewModel.manQuestionList[position])

        //item 을 킬릭시 바텀시트 표시
        holder.binding.itemFrame.setOnClickListener {
            viewModel.questionItemPosition = position
            val orderBottomDialogFragment: OrderBottomDialogFragment = OrderBottomDialogFragment {
                /*Toast.makeText(fragment.requireContext(), "성공적으로 반영되었습니다!", Toast.LENGTH_SHORT)
                    .show()*/

            }
            orderBottomDialogFragment.show(
                fragment.requireActivity().supportFragmentManager,
                orderBottomDialogFragment.tag
            )

        }

    }

    override fun getItemCount(): Int {
        return viewModel.manQuestionList.size
    }

    inner class AnswerRecyclerViewHolder(val binding: QuestionRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Question) {
            binding.data = data
            binding.executePendingBindings()
        }
    }
}