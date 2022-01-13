package com.example.imdb.scene.login

import com.example.imdb.R
import com.example.imdb.base.BaseFragment
import com.example.imdb.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(
    R.layout.fragment_login
)