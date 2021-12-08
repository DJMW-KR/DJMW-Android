package com.pss.djmw_android.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.pss.djmw_android.data.model.Post
import com.pss.djmw_android.data.model.UserInfo
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firestore: FirebaseFirestore
) {
    //게시판 게시물 가져오기
    fun getPost() = firestore.collection("post").get()

    //게시판 게시물 작성하기
    fun setPost(post : Post) = firestore.collection("post").document().set(post)
}