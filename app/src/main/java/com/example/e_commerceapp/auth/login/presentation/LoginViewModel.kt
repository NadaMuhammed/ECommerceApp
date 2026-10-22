package com.example.e_commerceapp.auth.login.presentation

import androidx.lifecycle.viewModelScope
import com.example.base.BaseUseCase
import com.example.base.BaseViewModel
import com.example.e_commerceapp.auth.login.domain.model.entity.LoginEntity
import com.example.e_commerceapp.auth.login.domain.model.input.LoginInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: BaseUseCase<LoginInput, LoginEntity?>
) : BaseViewModel<LoginContract.State,
        LoginContract.SideEffect,
        LoginContract.Event>(
    initialState = LoginContract.State()
) {

    override fun setEvent(newEvent: LoginContract.Event) {
        when (newEvent) {
            is LoginContract.Event.SetEmail -> {
                setEmail(
                    newEvent.email
                )
            }

            is LoginContract.Event.SetPassword -> {
                setPassword(
                    newEvent.password
                )
            }

            is LoginContract.Event.ShowEmailError -> {
                showEmailError()
            }

            is LoginContract.Event.ShowPasswordError -> {
                showPasswordError()
            }

            is LoginContract.Event.LoginBiometric -> {
                loginBiometric()
            }

            is LoginContract.Event.SetBiometric -> {
                setBiometric()
            }

            is LoginContract.Event.NavigateToHome -> {
                navigateToHome()
            }

            is LoginContract.Event.LoginNormally -> {
                loginNormally()
            }
        }
    }

    private fun loginNormally() {
        if (state.value.isEmailError?.not() == true
            && state.value.isPasswordError?.not() == true
        ) {
            viewModelScope.launch {
                executeUseCase(
                    useCase = {
                        loginUseCase.invoke(
                            LoginInput(
                                email = state.value.email,
                                password = state.value.password
                            )
                        )
                    },
                    onLoading = {

                    },
                    onError = {

                    },
                    onSuccess = {
                        setSideEffect(
                            LoginContract.SideEffect.NavigateToHome
                        )
                    }
                )
            }
        }
    }

    private fun navigateToHome() {
        setSideEffect(
            LoginContract.SideEffect.NavigateToHome
        )
    }

    private fun setBiometric() {
        setSideEffect(
            LoginContract.SideEffect.NavigateToSettings
        )
    }

    private fun loginBiometric() {
        setSideEffect(
            LoginContract.SideEffect.LoginBiometric
        )
    }

    private fun setPassword(password: String) {
        updateState {
            it.copy(
                isPasswordError = false
            )
        }

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
                isEmailError = false
            )
        }

        updateState {
            it.copy(
                email = email
            )
        }
    }
}