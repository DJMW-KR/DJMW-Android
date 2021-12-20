package com.pss.djmw_android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pss.djmw_android.data.model.Question
import com.pss.djmw_android.data.model.UserInfo
import com.pss.djmw_android.data.model.UserParticipationInfo
import com.pss.djmw_android.repository.MainRepository
import com.pss.djmw_android.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    //가져온 남자 질문 정보
    val eventGetManQuestion: LiveData<Boolean> get() = _eventGetManQuestion
    private val _eventGetManQuestion = SingleLiveEvent<Boolean>()

    //가져온 남자 질문 정보
    val eventGetWomanQuestion: LiveData<Boolean> get() = _eventGetWomanQuestion
    private val _eventGetWomanQuestion = SingleLiveEvent<Boolean>()

    //가져온 유저 정보
    val eventGetUserInfo: LiveData<UserInfo> get() = _eventGetUserInfo
    private val _eventGetUserInfo = SingleLiveEvent<UserInfo>()

    //0 = 사용자 정보 가져오기 오류, 1 = 퀴즈 가져오기 오류, 2 = 사용자 랭킹 정보 가져오기 오류
    val eventError: LiveData<Int> get() = _eventError
    private val _eventError = SingleLiveEvent<Int>()

    //다른화면으로 넘어갈때 bottom nav bar 숨기기 or 보이기 (true = 보이기, false = 숨기기)
    val eventActionView: LiveData<Boolean> get() = _eventActionView
    private val _eventActionView = SingleLiveEvent<Boolean>()

    //사용자 랭킹 정보
    val eventUserRankingInfo: LiveData<Int> get() = _eventUserRankingInfo
    private val _eventUserRankingInfo = SingleLiveEvent<Int>()

    //사용자 질문 참여 통계 정보
    val eventUserParticipationInfo: LiveData<UserParticipationInfo> get() = _eventUserParticipationInfo
    private val _eventUserParticipationInfo = SingleLiveEvent<UserParticipationInfo>()

    //질문 더블 클릭 방지
    var questionClickEvent = false

    //가져온 남자 질문 값
    var manQuestionList = arrayListOf<Question>()

    //가져온 여자 질문 값
    var womanQuestionList = arrayListOf<Question>()

    //가져온 사용자 정보 score값 높은 순서대로
    var userRankingList = arrayListOf<UserInfo>()

    //질문 클릭시 몇번째 아이템인지 확인 값
    var questionItemPosition = 0


    fun setUserRankingInfo(content: Int) = _eventUserRankingInfo.postValue(content)

    fun setEventGetUserInfo(content: UserInfo) = _eventGetUserInfo.postValue(content)

    fun setEventUserParticipationInfo(content : UserParticipationInfo) = _eventUserParticipationInfo.postValue(content)

    fun setActionView(content: Boolean) = _eventActionView.postValue(content)

    fun setQuestionStatistics(questionName: String, choiceNumber: Int, result: Int, sex: String) =
        mainRepository.setQuestionStatistics(questionName, choiceNumber, result, sex)

    fun setAnswerStatistics(questionName: String, choiceNumber: Int, result: Int, sex: String) =
        mainRepository.setAnswerStatistics(questionName, choiceNumber, result, sex)

    fun getQuestionStatistics(questionName: String, choiceNumber: Int, sex: String) =
        mainRepository.getQuestionStatistics(questionName, choiceNumber, sex)

    fun getAnswerStatistics(questionName: String, choiceNumber: Int, sex: String) =
        mainRepository.getAnswerStatistics(questionName, choiceNumber, sex)

    fun getUserInfo(userUid: String) = try {
        mainRepository.getUserInfo(userUid)
            .addOnSuccessListener {
                _eventGetUserInfo.postValue(it.toObject(UserInfo::class.java)!!)
            }
            .addOnFailureListener {
                _eventError.postValue(0)
            }
    } catch (e: Exception) {

    }

    fun getUserRankingInfo() = mainRepository.userRankingInfo()
        .addOnSuccessListener {
            for (item in it.documents) {
                Log.d("로그", "가져온 랭킹 정보 : $item")
                userRankingList.add(item.toObject(UserInfo::class.java)!!)
            }
            for (item in 0..userRankingList.size) {
                //사용자 id
                if (_eventGetUserInfo.value?.userId == userRankingList[item].userId) {
                    _eventUserRankingInfo.postValue(item + 1)
                    break
                }
            }
        }
        .addOnFailureListener {
            _eventError.postValue(2)
        }

    fun getManQuestion() = try {
        mainRepository.getManQuestion()
            .addOnSuccessListener {
                viewModelScope.launch {
                    for (item in it.documents) {
                        manQuestionList.add(item.toObject(Question::class.java)!!)
                    }
                }
                _eventGetManQuestion.postValue(true)

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
                _eventError.postValue(1)
            }
    } catch (e: Exception) {
        Log.e("TAG", "getQuestion 메서드 오류 : $e")
    }

    fun getWomanQuestion() = try {
        mainRepository.getWomanQuestion()
            .addOnSuccessListener {
                viewModelScope.launch {
                    for (item in it.documents) {
                        womanQuestionList.add(item.toObject(Question::class.java)!!)
                    }
                }
                _eventGetWomanQuestion.postValue(true)

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
                _eventError.postValue(1)
            }
    } catch (e: Exception) {
        Log.e("TAG", "getQuestion 메서드 오류 : $e")
    }
}