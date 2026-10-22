package com.example.e_commerceapp.auth.login.presentation

import com.example.base.BaseContract

sealed class LoginContract {

    data class State(
        val isEmailError: Boolean? = null,
        val isPasswordError: Boolean? = null,
        val email: String? = null,
        val password: String? = null
    ) : BaseContract.State

    sealed class SideEffect : BaseContract.SideEffect {

        data object ShowEmailError : SideEffect()

        data object ShowPasswordError : SideEffect()

        data object LoginBiometric : SideEffect()

        data object NavigateToSettings : SideEffect()

        data object NavigateToHome : SideEffect()
    }

    sealed class Event : BaseContract.Event {

        data class SetEmail(
            val email: String
        ) : Event()

        data class SetPassword(
            val password: String
        ) : Event()

        data object ShowEmailError : Event()

        data object ShowPasswordError : Event()

        data object LoginBiometric : Event()

        data object LoginNormally : Event()

        data object SetBiometric : Event()

        data object NavigateToHome : Event()
    }
}