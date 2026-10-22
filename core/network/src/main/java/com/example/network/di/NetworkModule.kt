package com.example.network.di

import com.example.network.BuildConfig
import com.example.network.interceptors.LoginInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    @Named("app")
    fun provideAppRetrofit(
        client: OkHttpClient,
        converterFactory: MoshiConverterFactory
    ): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.API_BASE_URL)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    @Named("auth")
    fun provideAuthRetrofit(
        @Named("auth-client") client: OkHttpClient,
        converterFactory: MoshiConverterFactory
    ): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.AUTH_BASE_URL)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    @Named("auth-client")
    fun provideAuthClient(
        loggingInterceptor: HttpLoggingInterceptor,
        loginInterceptor: LoginInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(loginInterceptor)
        .build()

    @Singleton
    @Provides
    @Named("app-client")
    fun provideClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideLoginInterceptor(): LoginInterceptor = LoginInterceptor()

    @Singleton
    @Provides
    fun provideConverterFactory(
        moshi: Moshi
    ): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    @Singleton
    @Provides
    fun provideMoshi(
        kotlinJsonAdapterFactory: KotlinJsonAdapterFactory
    ): Moshi = Moshi.Builder()
        .add(kotlinJsonAdapterFactory)
        .build()

    @Singleton
    @Provides
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()
}