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

    //firebase rtdb에서 통계 가져오기
    fun getAnswerStatistics(
        questionName: String,
        choiceNumber: Int,
        sex: String
    ): Task<DataSnapshot> {
        return when (choiceNumber) {
            0 -> getChoiceNumberAnswerStatistics(questionName, "statistics_one", sex)
            1 -> getChoiceNumberAnswerStatistics(questionName, "statistics_two", sex)
            2 -> getChoiceNumberAnswerStatistics(questionName, "statistics_three", sex)
            3 -> getChoiceNumberAnswerStatistics(questionName, "statistics_four", sex)
            4 -> getChoiceNumberAnswerStatistics(questionName, "statistics_five", sex)
            else -> getChoiceNumberAnswerStatistics(questionName, "statistics_one", sex)
        }
    }

    //user 정보 가져오기
    fun getUserInfo(userUid: String) = firestore.collection("userInfo").document(userUid).get()

    //firebase rtdb의 통계 값에 +1하기
    fun setQuestionStatistics(
        questionName: String,
        choiceNumber: Int,
        result: Int,
        sex: String
    ): Task<Void> {
        return when (choiceNumber) {
            0 -> setChoiceQuestionStatistics(questionName, "statistics_one", result, sex)
            1 -> setChoiceQuestionStatistics(questionName, "statistics_two", result, sex)
            2 -> setChoiceQuestionStatistics(questionName, "statistics_three", result, sex)
            3 -> setChoiceQuestionStatistics(questionName, "statistics_four", result, sex)
            4 -> setChoiceQuestionStatistics(questionName, "statistics_five", result, sex)
            else -> setChoiceQuestionStatistics(questionName, "statistics_one", result, sex)
        }
    }

    //firebase rtdb의 통계 값에 +1하기
    fun setAnswerStatistics(
        questionName: String,
        choiceNumber: Int,
        result: Int,
        sex: String
    ): Task<Void> {
        return when (choiceNumber) {
            0 -> setChoiceAnswerStatistics(questionName, "statistics_one", result, sex)
            1 -> setChoiceAnswerStatistics(questionName, "statistics_two", result, sex)
            2 -> setChoiceAnswerStatistics(questionName, "statistics_three", result, sex)
            3 -> setChoiceAnswerStatistics(questionName, "statistics_four", result, sex)
            4 -> setChoiceAnswerStatistics(questionName, "statistics_five", result, sex)
            else -> setChoiceAnswerStatistics(questionName, "statistics_one", result, sex)
        }
    }

    //랭킹 정보 가져오기
    fun userRankingInfo() =
        firestore.collection("userInfo").orderBy("score", Query.Direction.DESCENDING).get()

    //바텀 시트 질문에 대한 대답 선택시 대답 통계 Set (+1)
    private fun setChoiceQuestionStatistics(
        questionName: String,
        choice: String,
        result: Int,
        sex: String
    ) : Task<Void> {

        return when (sex) {
            "man" -> firebaseDatabase.reference.child("man_question").child(questionName)
                .child(choice)
                .setValue(result)
            "woman" -> {
                firebaseDatabase.reference.child("woman_question").child(questionName)
                    .child(choice)
                    .setValue(result)
            }
            else -> firebaseDatabase.reference.child("man_question").child(questionName)
                .child(choice)
                .setValue(result)
        }
    }

    //바텀 시트 질문에 대한 대답 선택시 대답 통계 Set (+1)
    private fun setChoiceAnswerStatistics(
        questionName: String,
        choice: String,
        result: Int,
        sex: String
    ) : Task<Void> {

        return when (sex) {
            "man" -> firebaseDatabase.reference.child("man_answer").child(questionName)
                .child(choice)
                .setValue(result)
            "woman" -> {
                firebaseDatabase.reference.child("woman_answer").child(questionName)
                    .child(choice)
                    .setValue(result)
            }
            else -> firebaseDatabase.reference.child("man_answer").child(questionName)
                .child(choice)
                .setValue(result)
        }
    }

    //대답하기 답변 선택시 대답 통계 Get
    private fun getChoiceNumberQuestionStatistics(questionName: String, num: String, sex: String) =
        if (sex == "man") {
            Log.d("로그", "getChoiceNumberQuestionStatistics if : $questionName, $num, $sex")
            firebaseDatabase.reference.child("man_question").child(questionName)
                .child(num).get()
        } else {
            Log.d("로그", "getChoiceNumberQuestionStatistics else : $questionName, $num, $sex")
            firebaseDatabase.reference.child("woman_question").child(questionName).child(num).get()
        }

    //선택하기 대변 선택시 대답 통계 Get
    private fun getChoiceNumberAnswerStatistics(questionName: String, num: String, sex: String) =
        if (sex == "man") {
            Log.d("로그", "getChoiceNumberQuestionStatistics if : $questionName, $num, $sex")
            firebaseDatabase.reference.child("man_answer").child(questionName)
                .child(num).get()
        } else {
            Log.d("로그", "getChoiceNumberQuestionStatistics else : $questionName, $num, $sex")
            firebaseDatabase.reference.child("woman_answer").child(questionName).child(num).get()
        }
}