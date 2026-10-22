package com.example.e_commerceapp.auth.login.domain.useCase

import com.example.base.BaseResult
import com.example.base.BaseUseCase
import com.example.e_commerceapp.auth.login.domain.model.entity.LoginEntity
import com.example.e_commerceapp.auth.login.domain.model.input.LoginInput
import com.example.e_commerceapp.auth.login.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) : BaseUseCase<LoginInput, LoginEntity?>() {

    override suspend fun invoke(input: LoginInput): BaseResult<LoginEntity?> {
        return loginRepository.login(
            loginInput = input
        )
    }
}