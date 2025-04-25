package com.example.base

sealed class BaseResult<T> {

    data class BaseSuccess<T>(
        val data: T? = null
    ): BaseResult<T>()

    data object BaseFailure: BaseResult<Nothing>()
}