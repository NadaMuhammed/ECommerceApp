package com.example.managers.biometricManager

import android.content.Context

interface BiometricManager {

    fun authenticate(
        context: Context,
        onSuccess: () -> Unit,
        onFailure: () -> Unit
    )

    fun handleBiometricError(
        context: Context,
        action: () -> Unit
    )
}