package com.pss.djmw_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pss.djmw_android.widget.utils.SingleLiveEvent

class SignInViewModel : ViewModel() {
    val eventKakaoLogin: LiveData<Any> get() = _eventKakaoLogin
    private val _eventKakaoLogin = SingleLiveEvent<Any>()

    fun clickKakaoLoginBtn() = _eventKakaoLogin.call()
}