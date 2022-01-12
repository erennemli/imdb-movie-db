package com.example.imdb.scene.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdb.base.BaseViewModel
import com.example.imdb.data.remote.model.search.multi.SearchMultiResponseModel
import com.example.imdb.domain.SearchMultiUseCase
import com.example.imdb.view.uimodel.SearchMultiUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchMultiUseCase: SearchMultiUseCase,
    application: Application
) : BaseViewModel(application) {

    val searchMultiLiveData: MutableLiveData<List<SearchMultiUiModel?>> = MutableLiveData()

    fun searchMulti(query: String?) = viewModelScope.launch {
        searchMultiUseCase.run(params = SearchMultiUseCase.Params(query = query))
            .either(::handleFailure, ::handleSearchMulti)
    }

    private fun handleSearchMulti(response: SearchMultiResponseModel?) {
        searchMultiLiveData.postValue(response?.resultSearchMulti?.map { it.toUiModel() })
    }
}