package com.example.e_commerceapp.auth.login.presentation

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.base.BaseFragment
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentLoginBinding
import com.example.watchers.EmailTextWatcher
import com.example.watchers.TextWatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {

    private val viewModel: LoginViewModel by viewModels()

    private val emailTextWatcher by lazy {
        EmailTextWatcher(
            onTextChanged = { email ->
                viewModel.setEvent(
                    LoginContract.Event.SetEmail(
                        email = email
                    )
                )
            },
            onError = {
                viewModel.setEvent(
                    LoginContract.Event.ShowEmailError
                )
            }
        )
    }

    private val textWatcher by lazy {
        TextWatcher(
            onTextChanged = {
                viewModel.setEvent(
                    LoginContract.Event.SetPassword(
                        it
                    )
                )
            },
            onError = {
                viewModel.setEvent(
                    LoginContract.Event.ShowPasswordError
                )
            }
        )
    }

    override fun FragmentLoginBinding.registerListeners() {
        tedUsername.addTextChangedListener(emailTextWatcher)
        tedPassword.addTextChangedListener(textWatcher)
    }

    override fun handleState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                with(binding) {
                    if (state.isEmailError.not()) tilUsername.error = null
                    if (state.isPasswordError.not()) tilPassword.error = null
                    btnLogin.isEnabled = state.isEmailError.not() && state.isPasswordError.not()
                }
            }
        }
    }

    override fun handleSideEffect() {
        lifecycleScope.launch {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is LoginContract.SideEffect.ShowEmailError -> {
                        binding.tilUsername.error = getString(
                            R.string.username_error
                        )
                    }

                    is LoginContract.SideEffect.ShowPasswordError -> {
                        binding.tilPassword.error = getString(
                            R.string.password_error
                        )
                    }
                }
            }
        }
    }
}