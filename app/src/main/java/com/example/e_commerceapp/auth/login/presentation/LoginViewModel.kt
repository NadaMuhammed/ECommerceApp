package com.example.e_commerceapp.auth.login.presentation

import com.example.base.BaseUseCase
import com.example.base.BaseViewModel
import com.example.e_commerceapp.auth.login.domain.model.entity.LoginEntity
import com.example.e_commerceapp.auth.login.domain.model.input.LoginInput
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    loginUseCase: BaseUseCase<LoginInput, LoginEntity?>
) : BaseViewModel<LoginContract.State,
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
                setPassword(
                    password = newEvent.password
                )
            }

            is LoginContract.Event.ShowEmailError -> {
                showEmailError()
            }

            is LoginContract.Event.ShowPasswordError -> {
                showPasswordError()
            }
        }
    }

    private fun setPassword(password: String) {
        updateState {
            it.copy(
                password = password
            )
        }
    }

    private fun showEmailError() {
        updateState {
            it.copy(
                isEmailError = true
            )
        }

        setSideEffect(
            LoginContract.SideEffect.ShowEmailError
        )
    }

    private fun showPasswordError() {
        updateState {
            it.copy(
                isPasswordError = true
            )
        }

        setSideEffect(
            LoginContract.SideEffect.ShowPasswordError
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