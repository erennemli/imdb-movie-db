package com.example.imdb.scene.movies

import android.app.Application
import com.example.imdb.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application)