package com.pss.djmw_android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pss.djmw_android.data.model.UserParticipationInfo
import com.pss.djmw_android.repository.SplashRepository
import com.pss.djmw_android.widget.utils.DataStoreModule
import com.pss.djmw_android.widget.utils.SingleLiveEvent
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
    //getUserParticipationInfo 성공 후 정보 저장
    val eventUserParticipationInfo: LiveData<UserParticipationInfo> get() = _eventUserParticipationInfo
    private val _eventUserParticipationInfo = SingleLiveEvent<UserParticipationInfo>()

    val eventError: LiveData<Int> get() = _eventError
    private val _eventError = SingleLiveEvent<Int>()

    fun checkAppVersion() = splashRepository.versionCheck()


    suspend fun readDataStore() : String {
        var check = "NO"
        viewModelScope.async {
            check = dataStoreModule.readUserInfoSave.first()
        }.await()
        return check
    }

    fun getUserParticipationInfo(id : String) = splashRepository.getUserParticipationInfo(id)
        .addOnSuccessListener {
            if (it.value == null) _eventError.postValue(0)
            else _eventUserParticipationInfo.postValue(it.getValue(UserParticipationInfo::class.java))
        }
        .addOnFailureListener {
            _eventError.postValue(0)
        }
}