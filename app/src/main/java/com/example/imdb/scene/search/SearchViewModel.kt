package com.example.imdb.scene.search

import android.app.Application
import com.example.imdb.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application)