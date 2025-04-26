package com.example.e_commerceapp.auth.login.data.service

import com.example.e_commerceapp.auth.login.data.dto.request.LoginRequest
import com.example.e_commerceapp.auth.login.data.dto.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

fun interface LoginService {

    @POST("/api/login")
    @Header()
    fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse
}