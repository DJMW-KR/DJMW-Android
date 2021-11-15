package com.pss.djmw_android.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.pss.djmw_android.repository.SplashRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSplashRepository(
        firebaseDatabase: FirebaseDatabase,
        firestore: FirebaseFirestore
    ) = SplashRepository(firebaseDatabase, firestore)
}