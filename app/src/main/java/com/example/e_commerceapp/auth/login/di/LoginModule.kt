package com.example.e_commerceapp.auth.login.di

import com.example.base.BaseUseCase
import com.example.e_commerceapp.auth.login.data.repository.LoginRepositoryImpl
import com.example.e_commerceapp.auth.login.data.service.LoginService
import com.example.e_commerceapp.auth.login.domain.model.entity.LoginEntity
import com.example.e_commerceapp.auth.login.domain.model.input.LoginInput
import com.example.e_commerceapp.auth.login.domain.repository.LoginRepository
import com.example.e_commerceapp.auth.login.domain.useCase.LoginUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
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

@Module
@InstallIn(ViewModelComponent::class)
interface BindingModule {

    @Binds
    fun bindLoginRepository(
        loginRepositoryImpl: LoginRepositoryImpl
    ): LoginRepository

    @Binds
    fun bindLoginUseCase(
        loginUseCase: LoginUseCase
    ): BaseUseCase<LoginInput, LoginEntity?>
}