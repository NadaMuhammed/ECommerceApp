package com.example.e_commerceapp.auth.login.domain.repository

import com.example.e_commerceapp.auth.login.domain.model.entity.LoginEntity
import com.example.e_commerceapp.auth.login.domain.model.input.LoginInput

fun interface LoginRepository {

    suspend fun login(
        loginInput: LoginInput
    ): LoginEntity?
}