package com.example.network.interceptors

import com.example.network.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class LoginInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestWithApiKey = chain.request()
            .newBuilder()
            .header("x-api-key", BuildConfig.AUTH_API_KEY)
            .addHeader("Content-Type", "application/json")
            .build()

        return chain.proceed(requestWithApiKey)
    }
}