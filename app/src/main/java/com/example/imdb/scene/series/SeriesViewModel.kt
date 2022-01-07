package com.example.imdb.scene.series

import android.app.Application
import com.example.imdb.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    application: Application
) : BaseViewModel(application)