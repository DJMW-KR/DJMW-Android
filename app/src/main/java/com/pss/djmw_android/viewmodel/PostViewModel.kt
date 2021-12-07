package com.pss.djmw_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pss.djmw_android.repository.PostRepository
import com.pss.djmw_android.repository.SignInRepository
import com.pss.djmw_android.widget.utils.DataStoreModule
import com.pss.djmw_android.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel  @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    val eventGetPostResponse: LiveData<Any?> get() = _eventGetPostResponse
    private val _eventGetPostResponse = MutableLiveData<Any?>()

    val eventError: LiveData<Int> get() = _eventError
    private val _eventError = SingleLiveEvent<Int>()


    init {
        _eventGetPostResponse.postValue(null)
    }


    fun getPost()= postRepository.getPost()
        .addOnSuccessListener {
            _eventGetPostResponse.postValue(it.value)
        }
        .addOnFailureListener {
            _eventError.postValue(0)
        }
}