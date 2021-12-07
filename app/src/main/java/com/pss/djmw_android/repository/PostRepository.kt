package com.pss.djmw_android.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.pss.djmw_android.data.model.UserInfo
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firestore: FirebaseFirestore
) {
    fun getPost() = firebaseDatabase.reference.child("post").get()
}