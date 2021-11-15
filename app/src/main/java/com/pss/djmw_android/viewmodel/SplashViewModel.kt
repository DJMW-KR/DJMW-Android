package com.pss.djmw_android.viewmodel

import androidx.lifecycle.ViewModel
import com.pss.djmw_android.repository.SplashRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashRepository: SplashRepository
): ViewModel() {

    fun checkAppVersion() = splashRepository.versionCheck()
}