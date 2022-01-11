package com.example.imdb.scene.series

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdb.base.BaseViewModel
import com.example.imdb.data.remote.model.series.toprated.TopRatedSeriesResponseModel
import com.example.imdb.domain.TopRatedSeriesUseCase
import com.example.imdb.util.general.ListAdapterItem
import com.example.imdb.util.general.UseCase
import com.example.imdb.util.listener.ListAdapterClickListener
import com.example.imdb.view.uimodel.TopRatedSeriesUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val topRatedSeriesUseCase: TopRatedSeriesUseCase,
    application: Application
) : BaseViewModel(application), ListAdapterClickListener<ListAdapterItem> {

    val topRatedSeriesLiveData: MutableLiveData<List<TopRatedSeriesUiModel>> = MutableLiveData()

    fun getTopRatedSeries() = viewModelScope.launch {
        topRatedSeriesUseCase.run(UseCase.None).either(
            ::handleFailure, ::handleTopRatedSeries
        )
    }

    private fun handleTopRatedSeries(list: TopRatedSeriesResponseModel?) {
        topRatedSeriesLiveData.postValue(list?.results?.map { it.toUiModel() })
    }

    override fun onAdapterItemClicked(uiModel: ListAdapterItem) {

    }
}