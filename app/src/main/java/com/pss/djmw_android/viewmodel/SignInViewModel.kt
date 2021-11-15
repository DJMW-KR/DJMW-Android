package com.pss.djmw_android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pss.djmw_android.data.model.UserInfo
import com.pss.djmw_android.repository.SignInRepository
import com.pss.djmw_android.repository.SplashRepository
import com.pss.djmw_android.widget.utils.DataStoreModule
import com.pss.djmw_android.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInRepository: SignInRepository,
    private val dataStoreModule: DataStoreModule
) : ViewModel() {
    val eventKakaoLogin: LiveData<Any> get() = _eventKakaoLogin
    private val _eventKakaoLogin = SingleLiveEvent<Any>()

    val eventSaveDataStoreSuccess: LiveData<String> get() = _eventSaveDataStoreSuccess
    private val _eventSaveDataStoreSuccess = SingleLiveEvent<String>()

    fun clickKakaoLoginBtn() = _eventKakaoLogin.call()

    fun setUserInfoFirebase(userInfo: UserInfo) = viewModelScope.launch {
        signInRepository.setUserInfo(userInfo)
            .addOnSuccessListener {
                val result = "YES"
                viewModelScope.launch {
                    dataStoreModule.setUserInfoSave(result)
                    _eventSaveDataStoreSuccess.postValue(result)
                }
            }
            .addOnFailureListener {
                val result = "NO"
                viewModelScope.launch {
                    dataStoreModule.setUserInfoSave(result)
                    _eventSaveDataStoreSuccess.postValue(result)
                }
            }
    }
}