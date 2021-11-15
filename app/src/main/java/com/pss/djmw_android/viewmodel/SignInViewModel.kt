package com.pss.djmw_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pss.djmw_android.repository.SplashRepository
import com.pss.djmw_android.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
) : ViewModel() {
    val eventKakaoLogin: LiveData<Any> get() = _eventKakaoLogin
    private val _eventKakaoLogin = SingleLiveEvent<Any>()

    fun clickKakaoLoginBtn() = _eventKakaoLogin.call()
}