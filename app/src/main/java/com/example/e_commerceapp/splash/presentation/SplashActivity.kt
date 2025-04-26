package com.example.e_commerceapp.splash.presentation

import android.content.Intent
import android.os.Handler
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.base.BaseActivity
import com.example.e_commerceapp.auth.AuthActivity
import com.example.e_commerceapp.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(
    ActivitySplashBinding::inflate
) {

    private val viewModel: SplashViewModel by viewModels()

    override fun ActivitySplashBinding.initializeUI() {
        viewModel.setEvent(
            SplashContract.Event.ShowCustomSplash
        )
    }

    override fun handleSideEffect() {
        lifecycleScope.launch {
            viewModel.sideEffect.collect { sideEffect ->
                when (sideEffect) {
                    is SplashContract.SideEffect.NavigateToAuthentication -> {
                        val intent = Intent(
                            this@SplashActivity,
                            AuthActivity::class.java
                        )
                        startActivity(intent)
                    }

                    is SplashContract.SideEffect.NavigateToLogin -> {
                        //TODO: Navigate To Login
                    }

                    is SplashContract.SideEffect.ShowCustomSplash -> {
                        Handler().postDelayed({
                            viewModel.setEvent(
                                SplashContract.Event.NavigateToAuthentication
                            )
                        }, sideEffect.splashTime)
                    }
                }
            }
        }
    }
}