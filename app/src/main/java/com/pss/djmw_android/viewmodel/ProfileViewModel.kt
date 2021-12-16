package com.pss.djmw_android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pss.djmw_android.data.model.Inquire
import com.pss.djmw_android.repository.PostRepository
import com.pss.djmw_android.repository.ProfileRepository
import com.pss.djmw_android.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {
    val eventSetInquireSuccess: LiveData<Any> get() = _eventSetInquireSuccess
    private val _eventSetInquireSuccess = SingleLiveEvent<Any>()

    val eventError: LiveData<Int> get() = _eventError
    private val _eventError = SingleLiveEvent<Int>()


    fun setInquire(inquire: Inquire) = profileRepository.setInquire(inquire)
        .addOnSuccessListener {
            Log.d("로그","문의사항 : $it")
            _eventSetInquireSuccess.call()
        }
        .addOnFailureListener {
            _eventError.postValue(0)
        }
}