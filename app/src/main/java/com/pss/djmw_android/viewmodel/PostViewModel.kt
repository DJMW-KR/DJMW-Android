package com.pss.djmw_android.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pss.djmw_android.data.model.Post
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

    val eventSetPostSuccess: LiveData<Any> get() = _eventSetPostSuccess
    private val _eventSetPostSuccess = SingleLiveEvent<Any>()

    //0 = getPostError, 1 = SetPostError
    val eventError: LiveData<Int> get() = _eventError
    private val _eventError = SingleLiveEvent<Int>()

    // val postList = arrayListOf<Post>()
    lateinit var postList: ArrayList<Post>


    fun initArrayList() {
        postList = arrayListOf<Post>()
    }

    fun getPost() = postRepository.getPost()
        .addOnSuccessListener {
            postList = arrayListOf<Post>()
            for (item in it.documents) {
                item.toObject(Post::class.java).let {
                    Log.d("로그", "postrepository : $it")
                    postList.add(it!!)
                }
            }
            _eventGetPostSuccess.call()
            //_eventGetPostResponse.postValue(it.getValue(GetPost::class.java))
        }
        .addOnFailureListener {
            _eventError.postValue(0)
        }

    fun setPost(post: Post) = postRepository.setPost(post)
        .addOnSuccessListener {
            _eventSetPostSuccess.call()
        }
        .addOnFailureListener {
            _eventError.postValue(1)
        }
}