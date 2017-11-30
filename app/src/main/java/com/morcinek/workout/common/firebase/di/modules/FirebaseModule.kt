package com.morcinek.workout.common.firebase.di.modules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.morcinek.workout.common.di.modules.AndroidModule
import com.morcinek.workout.common.firebase.auth.UserManager
import com.morcinek.workout.common.firebase.database.ReferenceManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(AndroidModule::class))
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseDatabase() = FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    fun provideUserManager(firebaseAuth: FirebaseAuth) = UserManager(firebaseAuth)

    @Provides
    @Singleton
    fun provideFirebaseDataManager(firebaseAuth: FirebaseAuth, firebaseDatabase: FirebaseDatabase)
            = ReferenceManager(firebaseAuth, firebaseDatabase)
}
