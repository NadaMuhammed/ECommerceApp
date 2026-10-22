package com.example.e_commerceapp.auth

import com.example.base.BaseActivity
import com.example.e_commerceapp.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : BaseActivity<ActivityAuthBinding>(
    ActivityAuthBinding::inflate
)