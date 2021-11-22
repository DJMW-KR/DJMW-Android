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
    private val fragment : Fragment
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
        Log.d("TAG", "AnswerRecyclerViewAdapter안 questionList : ${viewModel.questionList}")
        holder.bind(viewModel.questionList[position])

        holder.binding.itemFrame.setOnClickListener {
            viewModel.questionItemPosition = position
            val orderBottomDialogFragment: OrderBottomDialogFragment = OrderBottomDialogFragment {
                Log.d("TAG","bottomDialogSheet item : $it")
             /*   when (it) {
                    0 -> Toast.makeText(this, "추천순", Toast.LENGTH_SHORT).show()
                    1 -> Toast.makeText(this, "리뷰순", Toast.LENGTH_SHORT).show()
                }*/
            }
            orderBottomDialogFragment.show(fragment.requireActivity().supportFragmentManager, orderBottomDialogFragment.tag)

        }

    }

    override fun getItemCount(): Int {
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