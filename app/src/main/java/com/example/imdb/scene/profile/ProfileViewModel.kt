package com.example.imdb.scene.profile

import android.app.Application
import com.example.imdb.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application)