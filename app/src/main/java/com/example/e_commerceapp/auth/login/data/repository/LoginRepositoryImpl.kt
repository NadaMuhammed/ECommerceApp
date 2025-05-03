package com.example.e_commerceapp.auth.login.data.repository

import com.example.e_commerceapp.auth.login.data.service.LoginService
import com.example.e_commerceapp.auth.login.domain.mapper.LoginRequestMapper
import com.example.e_commerceapp.auth.login.domain.mapper.LoginResponseMapper
import com.example.e_commerceapp.auth.login.domain.model.entity.LoginEntity
import com.example.e_commerceapp.auth.login.domain.model.input.LoginInput
import com.example.e_commerceapp.auth.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginService: LoginService,
    private val loginRequestMapper: LoginRequestMapper,
    private val loginResponseMapper: LoginResponseMapper
): LoginRepository {

    override suspend fun login(loginInput: LoginInput): LoginEntity? {
        return loginResponseMapper.map(
            loginService.login(
                loginRequestMapper.map(loginInput)
            )
        )
    }
}