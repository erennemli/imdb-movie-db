package com.example.imdb.scene.login

import android.app.Application
import com.example.imdb.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(application: Application) : BaseViewModel(application) {
    fun navigateToMovie() {
        navigate(
            LoginFragmentDirections.actionLoginFragmentToMovieFragment()
        )
    }
}