package com.example.e_commerceapp.auth.login.presentation

import android.content.Intent
import android.hardware.biometrics.BiometricManager.Authenticators
import android.os.Build
import android.provider.Settings
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.base.BaseFragment
import com.example.e_commerceapp.R
import com.example.e_commerceapp.databinding.FragmentLoginBinding
import com.example.e_commerceapp.home.HomeActivity
import com.example.managers.biometricManager.BiometricManager
import com.example.watchers.EmailTextWatcher
import com.example.watchers.TextWatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    FragmentLoginBinding::inflate
) {
    @Inject
    lateinit var biometricManager: BiometricManager

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
        btnBiometric.setOnClickListener {
            viewModel.setEvent(
                LoginContract.Event.LoginBiometric
            )
        }
        btnLogin.setOnClickListener {
            viewModel.setEvent(
                LoginContract.Event.LoginNormally
            )
        }
    }

    override fun handleState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                with(binding) {
                    if (state.isEmailError?.not() == true) tilUsername.error = null
                    if (state.isPasswordError?.not() == true) tilPassword.error = null
                    btnLogin.isEnabled = state.isEmailError?.not() == true &&
                            state.isPasswordError?.not() == true
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

                    is LoginContract.SideEffect.LoginBiometric -> {
                        biometricManager.authenticate(
                            requireActivity(),
                            {
                                viewModel.setEvent(
                                    LoginContract.Event.NavigateToHome
                                )
                            },
                            {
                                viewModel.setEvent(
                                    LoginContract.Event.SetBiometric
                                )
                            }
                        )
                    }

                    is LoginContract.SideEffect.NavigateToSettings -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                            val intent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                                putExtra(
                                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                    Authenticators.BIOMETRIC_STRONG or
                                            Authenticators.DEVICE_CREDENTIAL
                                )
                            }
                            startActivity(intent)
                        } else {
                            val intent = Intent(Settings.ACTION_SECURITY_SETTINGS)
                            startActivity(intent)
                        }
                    }

                    is LoginContract.SideEffect.NavigateToHome -> {
                        val intent = Intent(requireActivity(), HomeActivity::class.java)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}