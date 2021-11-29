package com.pss.djmw_android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.QuerySnapshot
import com.pss.djmw_android.data.model.Question
import com.pss.djmw_android.repository.MainRepository
import com.pss.djmw_android.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.EOFException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    val eventGetQuestion: LiveData<Boolean> get() = _eventGetQuestion
    private val _eventGetQuestion = SingleLiveEvent<Boolean>()

    val userInfoSave: LiveData<Boolean> get() = _userInfoSave
    private val _userInfoSave = SingleLiveEvent<Boolean>()

    //가져온 질문 값
    var questionList = arrayListOf<Question>()

    //질문 클릭시 몇번째 아이템인지 확인 값
    var questionItemPosition = 0


    fun setQuestionStatistics(questionName: String, choiceNumber: Int, result: Int) = mainRepository.setQuestionStatistics(questionName, choiceNumber, result)

    fun getQuestionStatistics(questionName: String, choiceNumber: Int) = mainRepository.getQuestionStatistics(questionName, choiceNumber)

    fun getUserInfo(userUid : String) = try{
        mainRepository.getUserInfo(userUid)
    }catch (e : Exception){

    }

    fun getQuestion() = try {
        mainRepository.getQuestion()
            .addOnSuccessListener {
                viewModelScope.launch {
                    for (item in it.documents){
                        var question = item.toObject(Question::class.java)
                        Log.d("TAG","MainViewModel getQuestion 메소드 안 : $question")
                        questionList.add(question!!)
                    }
                }
                _eventGetQuestion.postValue(true)

                /*   Log.d("TAG","${it.documents[0]}")
                   //_eventGetQuestion.postValue(it)
                   for (document in it) {
                       for (key in 0 until document.data.keys.size){

                           Log.d("TAG","getQuestion : ${document.data.get(key)}")
                       }
                       //Log.d("TAG","getQuestion : ${document.id}, ${document.data.keys}, ${document.data.values}")
                   }*/
            }
            .addOnFailureListener {

            }
    }catch (e:Exception){
        Log.e("TAG","getQuestion 메서드 오류 : $e")
    }
}