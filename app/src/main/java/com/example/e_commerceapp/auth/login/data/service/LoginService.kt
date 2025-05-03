package com.example.e_commerceapp.auth.login.data.service

import com.example.e_commerceapp.auth.login.data.dto.request.LoginRequest
import com.example.e_commerceapp.auth.login.data.dto.response.LoginResponse
import com.example.network.BuildConfig
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

fun interface LoginService {

    @POST("/api/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse
}