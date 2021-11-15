package com.pss.djmw_android.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pss.djmw_android.repository.SplashRepository
import com.pss.djmw_android.widget.utils.DataStoreModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val splashRepository: SplashRepository,
    private val dataStoreModule: DataStoreModule
) : ViewModel() {

    fun checkAppVersion() = splashRepository.versionCheck()

    suspend fun readDataStore() : String {
        var check = "NO"
        viewModelScope.async {
            check = dataStoreModule.readUserInfoSave.first()
        }.await()
        return check
    }
}