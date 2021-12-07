package com.pss.djmw_android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.pss.djmw_android.data.model.GetPost
import com.pss.djmw_android.data.model.Question
import com.pss.djmw_android.repository.PostRepository
import com.pss.djmw_android.repository.SignInRepository
import com.pss.djmw_android.widget.utils.DataStoreModule
import com.pss.djmw_android.widget.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val postRepository: PostRepository
) : ViewModel() {
    val eventGetPostResponse: LiveData<ArrayList<GetPost>?> get() = _eventGetPostResponse
    private val _eventGetPostResponse = MutableLiveData<ArrayList<GetPost>?>()

    val eventError: LiveData<Int> get() = _eventError
    private val _eventError = SingleLiveEvent<Int>()


    init {
        _eventGetPostResponse.postValue(null)
    }


    fun getPost() = postRepository.getPost()
        .addOnSuccessListener { it ->
            for (item in it.documents) {
                item.toObject(GetPost::class.java).let {
                    _eventGetPostResponse.value?.add(it!!)
                }
            }
            //_eventGetPostResponse.postValue(it.getValue(GetPost::class.java))
        }
        .addOnFailureListener {
            _eventError.postValue(0)
        }
}