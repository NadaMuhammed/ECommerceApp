package com.example.e_commerceapp.splash.presentation

import com.example.base.BaseContract

sealed class SplashContract {

    data class State(
        val loadingTime: Long = 2000
    ): BaseContract.State

    sealed class SideEffect: BaseContract.SideEffect {
        data object NavigateToAuthentication: SideEffect()

        data object NavigateToLogin: SideEffect()

        data class ShowCustomSplash(
            val splashTime: Long
        ): SideEffect()
    }

    sealed class Event: BaseContract.Event {
        data object ShowCustomSplash: Event()

        data object NavigateToAuthentication: Event()

        data object NavigateToLogin: Event()
    }
}