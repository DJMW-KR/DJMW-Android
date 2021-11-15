package com.pss.djmw_android.di

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.pss.djmw_android.repository.SignInRepository
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
    fun provideSignInRepository(
        firebaseDatabase: FirebaseDatabase,
        firestore: FirebaseFirestore
    ) = SignInRepository(firebaseDatabase, firestore)
}