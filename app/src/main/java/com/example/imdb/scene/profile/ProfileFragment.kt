package com.example.imdb.scene.profile

import com.example.imdb.R
import com.example.imdb.base.BaseFragment
import com.example.imdb.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment<ProfileViewModel, FragmentProfileBinding>(
    R.layout.fragment_profile
)