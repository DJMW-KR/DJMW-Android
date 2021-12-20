package com.pss.djmw_android.repository

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SplashRepository @Inject constructor(
    private val firebaseDatabase: FirebaseDatabase,
    private val firestore: FirebaseFirestore
) {
    //앱 버전 체크
    fun versionCheck() = firebaseDatabase.reference.child("version").get()

    //사용자 퀴즈 참여 여부 통계정보 가져오기
    fun getUserParticipationInfo(id : String) = firebaseDatabase.reference.child("userParticipationInfo").child(id).get()
}