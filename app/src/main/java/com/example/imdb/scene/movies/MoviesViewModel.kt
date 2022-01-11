package com.example.imdb.scene.movies

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdb.base.BaseViewModel
import com.example.imdb.data.remote.model.movies.ongoing.GetOngoingMoviesResponseModel
import com.example.imdb.domain.OngoingMoviesUseCase
import com.example.imdb.util.general.Constants.Common.ROUNDED_RADIUS
import com.example.imdb.util.general.UseCase
import com.example.imdb.util.listener.ListAdapterClickListener
import com.example.imdb.view.uimodel.OngoingMovieUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCase: OngoingMoviesUseCase,
    application: Application
) : BaseViewModel(application), ListAdapterClickListener<OngoingMovieUiModel> {

    val ongoingMoviesList: MutableLiveData<List<OngoingMovieUiModel>> = MutableLiveData()

    fun getMovieInformationWithExpression() = viewModelScope.launch {
        moviesUseCase.run(UseCase.None).either(::handleFailure, ::handleOngoingMoviesList)
    }

    private fun handleOngoingMoviesList(list: GetOngoingMoviesResponseModel?) {
        var moviesList: List<OngoingMovieUiModel>? = null
        list?.results?.let {
            if (it.isNotEmpty()) {
                moviesList = it.map { movie -> movie.toUiModel(ROUNDED_RADIUS) }
            }
        }
        ongoingMoviesList.postValue(moviesList)
    }

    override fun onAdapterItemClicked(uiModel: OngoingMovieUiModel) {
    }
}