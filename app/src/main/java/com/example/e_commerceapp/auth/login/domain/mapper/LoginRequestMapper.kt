package com.example.e_commerceapp.auth.login.domain.mapper

import com.example.base.BaseMapper
import com.example.e_commerceapp.auth.login.data.dto.request.LoginRequest
import com.example.e_commerceapp.auth.login.domain.model.input.LoginInput
import javax.inject.Inject

class LoginRequestMapper @Inject constructor() : BaseMapper<LoginInput, LoginRequest>() {

    override fun map(input: LoginInput): LoginRequest {
        return LoginRequest(
            email = input.email,
            password = input.password
        )
    }
}