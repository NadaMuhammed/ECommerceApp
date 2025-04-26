package com.example.e_commerceapp.auth.login.presentation

import com.example.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel<
        LoginContract.State,
        LoginContract.SideEffect,
        LoginContract.Event>(
    initialState = LoginContract.State()
) {

    override fun setEvent(newEvent: LoginContract.Event) {
        when (newEvent) {
            is LoginContract.Event.SetEmail -> {
                setEmail(
                    email = newEvent.email
                )
            }

            is LoginContract.Event.SetPassword -> {

            }

            is LoginContract.Event.ShowEmailError -> {
                showEmailError()
            }
        }
    }

    private fun showEmailError() {
        setSideEffect(
            LoginContract.SideEffect.ShowEmailError
        )
    }

    private fun setEmail(email: String) {
        updateState {
            it.copy(
                email = email
            )
        }
    }
}