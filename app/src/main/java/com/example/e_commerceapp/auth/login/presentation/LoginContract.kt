package com.example.e_commerceapp.auth.login.presentation

import com.example.base.BaseContract

sealed class LoginContract {

    data class State(
        val isEmailError: Boolean = false,
        val isPasswordError: Boolean = false,
        val email: String? = null,
        val password: String? = null
    ) : BaseContract.State

    sealed class SideEffect : BaseContract.SideEffect {

        data object ShowEmailError: SideEffect()
    }

    sealed class Event : BaseContract.Event {

        data class SetEmail(
            val email: String
        ) : Event()

        data class SetPassword(
            val password: String
        ) : Event()

        data object ShowEmailError : Event()
    }
}