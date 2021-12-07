package com.pss.djmw_android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pss.djmw_android.data.model.GetPost
import com.pss.djmw_android.repository.PostRepository
import com.pss.djmw_android.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    val eventGetPostSuccess: LiveData<Any> get() = _eventGetPostSuccess
    private val _eventGetPostSuccess = SingleLiveEvent<Any>()

    val eventError: LiveData<Int> get() = _eventError
    private val _eventError = SingleLiveEvent<Int>()

    val postList = arrayListOf<GetPost>()


    fun getPost() = postRepository.getPost()
        .addOnSuccessListener {
            for (item in it.documents) {
                item.toObject(GetPost::class.java).let {
                    Log.d("로그","postrepository : $it")
                    postList.add(it!!)
                }
            }
            _eventGetPostSuccess.call()
            //_eventGetPostResponse.postValue(it.getValue(GetPost::class.java))
        }
        .addOnFailureListener {
            _eventError.postValue(0)
        }
}