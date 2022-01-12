package com.example.imdb.scene.series

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdb.base.BaseViewModel
import com.example.imdb.data.remote.model.series.genre.SeriesGenreResponseModel
import com.example.imdb.data.remote.model.series.popular.PopularSeriesResponseModel
import com.example.imdb.data.remote.model.series.toprated.TopRatedSeriesResponseModel
import com.example.imdb.domain.PopularSeriesUseCase
import com.example.imdb.domain.SeriesGenresUseCase
import com.example.imdb.domain.TopRatedSeriesUseCase
import com.example.imdb.util.general.Constants.Common.ROUNDED_RADIUS
import com.example.imdb.util.general.ListAdapterItem
import com.example.imdb.util.general.UseCase
import com.example.imdb.util.listener.ListAdapterClickListener
import com.example.imdb.view.uimodel.PopularSeriesUiModel
import com.example.imdb.view.uimodel.TopRatedSeriesUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val topRatedSeriesUseCase: TopRatedSeriesUseCase,
    private val seriesGenresUseCase: SeriesGenresUseCase,
    private val popularSeriesUseCase: PopularSeriesUseCase,
    application: Application
) : BaseViewModel(application), ListAdapterClickListener<ListAdapterItem> {

    val topRatedSeriesLiveData: MutableLiveData<List<TopRatedSeriesUiModel>> = MutableLiveData()
    val popularSeriesLiveData: MutableLiveData<List<PopularSeriesUiModel>> = MutableLiveData()

    fun getTopRatedSeries() = viewModelScope.launch {
        topRatedSeriesUseCase.run(UseCase.None).either(
            ::handleFailure, ::handleTopRatedSeries
        )
    }

    fun getSeriesGenres() = viewModelScope.launch {
        seriesGenresUseCase.run(UseCase.None).either(::handleFailure, ::handleSeriesGenres)
    }

    fun getPopularSeries() = viewModelScope.launch {
        popularSeriesUseCase.run(UseCase.None).either(::handleFailure, ::handlePopularSeries)
    }

    private fun handleTopRatedSeries(list: TopRatedSeriesResponseModel?) {
        list?.resultTopRatedSeries?.let { results ->
            if (results.isNotEmpty()) {
                topRatedSeriesLiveData.postValue(results.map { series ->
                    series.toUiModel(ROUNDED_RADIUS, getGenresAsString(series))
                })
            }
        }
    }

    private fun handleSeriesGenres(list: SeriesGenreResponseModel?) {
        list?.genres?.forEach { genre ->
            genres[genre.id ?: 0] = genre.name ?: ""
        }
    }

    private fun handlePopularSeries(list: PopularSeriesResponseModel?) {
        list?.resultPopularSeries?.let { results ->
            if (results.isNotEmpty()) {
                popularSeriesLiveData.postValue(results.map { series ->
                    series.toUiModel(ROUNDED_RADIUS, getGenresAsString(series))
                })
            }
        }
    }

    override fun onAdapterItemClicked(uiModel: ListAdapterItem) {

    }
}