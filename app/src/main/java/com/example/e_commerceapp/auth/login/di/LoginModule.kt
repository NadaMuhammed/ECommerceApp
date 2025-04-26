package com.example.e_commerceapp.auth.login.di

import com.example.e_commerceapp.auth.login.data.service.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProvidingModule {

    @Provides
    @Singleton
    fun provideService(
        @Named("auth") retrofit: Retrofit
    ) = retrofit.create(LoginService::class.java)
}