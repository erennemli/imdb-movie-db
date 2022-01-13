package com.example.imdb.scene.search

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdb.base.BaseViewModel
import com.example.imdb.data.remote.model.search.multi.SearchMultiResponseModel
import com.example.imdb.domain.SearchMultiUseCase
import com.example.imdb.util.general.Constants.Common.ROUNDED_RADIUS
import com.example.imdb.util.general.Constants.Search.QUERY_MIN_LENGTH
import com.example.imdb.util.general.Constants.Search.SEARCH_INTERVAL
import com.example.imdb.util.general.ListAdapterItem
import com.example.imdb.util.listener.ListAdapterClickListener
import com.example.imdb.view.uimodel.SearchMultiUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMultiUseCase: SearchMultiUseCase,
    application: Application
) : BaseViewModel(application), ListAdapterClickListener<ListAdapterItem> {

    val searchMultiLiveData: MutableLiveData<List<SearchMultiUiModel?>> = MutableLiveData()
    private val handler = Handler(Looper.getMainLooper())

    fun searchMulti(query: String?) = viewModelScope.launch {
        query?.let {
            if (it.length > QUERY_MIN_LENGTH) {
                handler.postDelayed({
                    requestSearchMultiResponse(query)
                }, SEARCH_INTERVAL)
            } else {
                searchMultiLiveData.postValue(null)
            }
        }
    }

    private fun requestSearchMultiResponse(query: String?) = viewModelScope.launch {
        searchMultiUseCase.run(params = SearchMultiUseCase.Params(query = query))
            .either(::handleFailure, ::handleSearchMulti)
    }

    private fun handleSearchMulti(response: SearchMultiResponseModel?) {
        searchMultiLiveData.postValue(response?.resultSearchMulti?.map { it.toUiModel(ROUNDED_RADIUS) })
    }

    override fun onAdapterItemClicked(uiModel: ListAdapterItem) {
    }
}