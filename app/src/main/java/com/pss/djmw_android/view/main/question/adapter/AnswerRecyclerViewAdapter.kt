package com.pss.djmw_android.view.main.question.adapter

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
    private val fragment: Fragment,
    private val state: State,
    private val sex: Sex
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
        when (state) {
            State.ANSWER -> StateAnswer(holder, position)
            State.ANSWERME -> StateAnswerMe(holder, position)
        }
    }

    private fun StateAnswer(holder: AnswerRecyclerViewHolder, position: Int) {
        when (sex) {
            Sex.WOMAN -> holder.bind(viewModel.womanQuestionList[position])
            Sex.MAN -> holder.bind(viewModel.manQuestionList[position])
        }

        //item 을 킬릭시 바텀시트 표시
        holder.binding.itemFrame.setOnClickListener {
            if (!viewModel.questionClickEvent) {
                viewModel.questionClickEvent = true
                viewModel.questionItemPosition = position
                val orderBottomDialogFragment: OrderBottomDialogFragment =
                    OrderBottomDialogFragment({
                        /*Toast.makeText(fragment.requireContext(), "성공적으로 반영되었습니다!", Toast.LENGTH_SHORT)
                            .show()*/
                    }, state = state, sex = sex)
                orderBottomDialogFragment.show(
                    fragment.requireActivity().supportFragmentManager,
                    orderBottomDialogFragment.tag
                )
            }
        }
    }

    private fun StateAnswerMe(holder: AnswerRecyclerViewHolder, position: Int) {
        when (sex) {
            Sex.WOMAN -> holder.bind(viewModel.manQuestionList[position])
            Sex.MAN -> holder.bind(viewModel.womanQuestionList[position])
        }
        //item 을 킬릭시 바텀시트 표시
        holder.binding.itemFrame.setOnClickListener {
            if (!viewModel.questionClickEvent) {
                viewModel.questionClickEvent = true
                viewModel.questionItemPosition = position
                val orderBottomDialogFragment: OrderBottomDialogFragment =
                    OrderBottomDialogFragment({
                        /*Toast.makeText(fragment.requireContext(), "성공적으로 반영되었습니다!", Toast.LENGTH_SHORT)
                            .show()*/
                    }, state = state, sex = sex)
                orderBottomDialogFragment.show(
                    fragment.requireActivity().supportFragmentManager,
                    orderBottomDialogFragment.tag
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return if (state == State.ANSWER) when (sex) {
            Sex.MAN -> viewModel.manQuestionList.size
            Sex.WOMAN -> viewModel.womanQuestionList.size
        } else when (sex) {
            Sex.MAN -> viewModel.womanQuestionList.size
            Sex.WOMAN -> viewModel.manQuestionList.size
        }
    }

    inner class AnswerRecyclerViewHolder(val binding: QuestionRecyclerViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Question) {
            binding.data = data
            binding.executePendingBindings()
        }
    }
}

enum class Sex {
    MAN,
    WOMAN
}

enum class State {
    ANSWER,
    ANSWERME
}