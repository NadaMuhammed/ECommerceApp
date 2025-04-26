package com.example.e_commerceapp.splash

import com.example.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor() : BaseViewModel<
        SplashContract.State,
        SplashContract.SideEffect,
        SplashContract.Event>(
    initialState = SplashContract.State()
) {

    override fun setEvent(newEvent: SplashContract.Event) {
        when (newEvent) {
            is SplashContract.Event.NavigateToAuthentication -> {
                navigateToAuthentication()
            }

            is SplashContract.Event.NavigateToLogin -> {
                //TODO: Navigate To Login
            }

            is SplashContract.Event.ShowCustomSplash -> {
                showCustomSplash()
            }
        }
    }

    private fun showCustomSplash() {
        setSideEffect(
            SplashContract.SideEffect.ShowCustomSplash(
                state.value.loadingTime
            )
        )
    }

    private fun navigateToAuthentication() {
        setSideEffect(
            SplashContract.SideEffect.NavigateToAuthentication
        )
    }
}