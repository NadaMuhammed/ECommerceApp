package com.example.e_commerceapp.auth.login.domain.mapper

import com.example.base.BaseMapper
import com.example.e_commerceapp.auth.login.data.dto.response.LoginResponse
import com.example.e_commerceapp.auth.login.domain.model.entity.LoginEntity
import javax.inject.Inject

class LoginResponseMapper @Inject constructor() : BaseMapper<LoginResponse?, LoginEntity?>() {

    override fun map(input: LoginResponse?): LoginEntity? {
        return input?.let {
            LoginEntity(
                token = it.token
            )
        }
    }
}