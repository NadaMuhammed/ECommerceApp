package com.example.managers.di

import com.example.managers.biometricManager.BiometricManager
import com.example.managers.biometricManager.BiometricManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object BaseBindModule {

    @Singleton
    @Provides
    fun provideBiometricManager(): BiometricManager = BiometricManagerImpl()
}