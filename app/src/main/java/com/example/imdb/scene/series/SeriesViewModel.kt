package com.example.imdb.scene.series

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.imdb.base.BaseViewModel
import com.example.imdb.data.remote.model.series.toprated.TopRatedSeriesResponseModel
import com.example.imdb.domain.TopRatedSeriesUseCase
import com.example.imdb.util.general.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val topRatedSeriesUseCase: TopRatedSeriesUseCase,
    application: Application
) : BaseViewModel(application) {

    fun getTopRatedSeries() = viewModelScope.launch {
        topRatedSeriesUseCase.run(UseCase.None).either(
            ::handleFailure, ::handleTopRatedSeries
        )
    }

    private fun handleTopRatedSeries(list: TopRatedSeriesResponseModel?) {
    }
}