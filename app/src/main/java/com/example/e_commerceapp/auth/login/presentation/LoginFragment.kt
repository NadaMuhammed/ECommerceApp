package com.example.e_commerceapp.auth.login.presentation

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.base.BaseFragment
import com.example.e_commerceapp.databinding.FragmentLoginBinding
import com.example.watchers.EmailTextWatcher
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
            showError = {
                viewModel.setEvent(
                    LoginContract.Event.ShowEmailError
                )
            }
        )
    }

    override fun FragmentLoginBinding.initializeUI() {
        binding.tilUsername.helperText = ""
    }

    override fun FragmentLoginBinding.registerListeners() {
        tedUsername.addTextChangedListener(emailTextWatcher)
    }

    override fun handleSideEffect() {
        lifecycleScope.launch {
            viewModel.sideEffect.collect { sideEffect ->
                when(sideEffect){
                    is LoginContract.SideEffect.ShowEmailError -> {
                        binding.tilUsername.error = "Enter Valid Email"
                    }
                }
            }
        }
    }
}