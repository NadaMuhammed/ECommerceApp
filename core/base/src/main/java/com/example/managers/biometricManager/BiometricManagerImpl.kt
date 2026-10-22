package com.example.managers.biometricManager

import android.app.AlertDialog
import android.content.Context
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.base.R
import javax.inject.Inject

class BiometricManagerImpl @Inject constructor() : BiometricManager {

    override fun authenticate(
        context: Context,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    ) {
        BiometricPrompt(
            context as androidx.fragment.app.FragmentActivity,
            ContextCompat.getMainExecutor(context),
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess.invoke()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    handleBiometricError(
                        context,
                        onFailure
                    )
                }

                override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    handleBiometricError(
                        context,
                        onFailure
                    )
                }
            }
        ).authenticate(
            BiometricPrompt.PromptInfo.Builder().setTitle(
                context.getString(
                    R.string.biometric_title
                )
            ).setSubtitle(
                context.getString(
                    R.string.biometric_subtitle
                )
            ).setNegativeButtonText(
                context.getString(
                    R.string.biometric_cancel
                )
            ).build()
        )
    }

    override fun handleBiometricError(
        context: Context,
        action: () -> Unit
    ) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(
            context.getString(
                R.string.biometric_failure_title
            )
        )
        builder.setMessage(
            context.getString(
                R.string.biometric_failure_subtitle
            )
        )
        builder.setPositiveButton(
            context.getString(
                R.string.go_to_settings
            )
        ) { _, _ ->
            action.invoke()
        }
        builder.setNegativeButton(
            context.getString(
                R.string.biometric_cancel
            )
        ) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}