package com.pss.djmw_android.repository

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.pss.djmw_android.data.model.Question
import com.pss.djmw_android.data.model.UserInfo
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firestore: FirebaseFirestore
) {
    val statisticsList = arrayListOf<String>(
        "statistics_one",
        "statistics_two",
        "statistics_three",
        "statistics_four",
        "statistics_five"
    )

    //남자 질문 가져오기
    fun getManQuestion() = firestore.collection("man_question").get()

    //여자 질문 가져오기
    fun getWomanQuestion() = firestore.collection("woman_question").get()


    //firebase rtdb에서 통계 가져오기
    fun getQuestionStatistics(
        questionName: String,
        choiceNumber: Int,
        sex: String
    ): Task<DataSnapshot> {
        return when (choiceNumber) {
            0 -> getChoiceNumberQuestionStatistics(questionName, "statistics_one", sex)
            1 -> getChoiceNumberQuestionStatistics(questionName, "statistics_two", sex)
            2 -> getChoiceNumberQuestionStatistics(questionName, "statistics_three", sex)
            3 -> getChoiceNumberQuestionStatistics(questionName, "statistics_four", sex)
            4 -> getChoiceNumberQuestionStatistics(questionName, "statistics_five", sex)
            else -> getChoiceNumberQuestionStatistics(questionName, "statistics_one", sex)
        }
    }

    //user 정보 가져오기
    fun getUserInfo(userUid: String) = firestore.collection("userInfo").document(userUid).get()

    //firebase rtdb의 통계 값에 +1하기
    fun setQuestionStatistics(questionName: String, choiceNumber: Int, result: Int): Task<Void> {
        return when (choiceNumber) {
            0 -> setChoiceQuestionStatistics(questionName, "statistics_one", result)
            1 -> setChoiceQuestionStatistics(questionName, "statistics_two", result)
            2 -> setChoiceQuestionStatistics(questionName, "statistics_three", result)
            3 -> setChoiceQuestionStatistics(questionName, "statistics_four", result)
            4 -> setChoiceQuestionStatistics(questionName, "statistics_five", result)
            else -> setChoiceQuestionStatistics(questionName, "statistics_one", result)
        }
    }

    //랭킹 정보 가져오기
    fun userRankingInfo() =
        firestore.collection("userInfo").orderBy("score", Query.Direction.DESCENDING).get()

    private fun setChoiceQuestionStatistics(questionName: String, choice: String, result: Int) =
        firebaseDatabase.reference.child("question").child(questionName).child(choice)
            .setValue(result)

    private fun getChoiceNumberQuestionStatistics(questionName: String, num: String, sex: String) =
        if (sex == "man") firebaseDatabase.reference.child("man_question").child(questionName).child(num).get()
        else firebaseDatabase.reference.child("woman_question").child(questionName).child(num).get()
}