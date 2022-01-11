package com.example.imdb.scene.movies

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdb.base.BaseViewModel
import com.example.imdb.data.remote.model.movies.genre.MovieGenresResponseModel
import com.example.imdb.data.remote.model.movies.ongoing.GetOngoingMoviesResponseModel
import com.example.imdb.data.remote.model.movies.ongoing.Result
import com.example.imdb.domain.MovieGenresUseCase
import com.example.imdb.domain.OngoingMoviesUseCase
import com.example.imdb.util.general.Constants.Common.COMMA_SEPARATOR
import com.example.imdb.util.general.Constants.Common.ROUNDED_RADIUS
import com.example.imdb.util.general.UseCase
import com.example.imdb.util.listener.ListAdapterClickListener
import com.example.imdb.view.uimodel.OngoingMovieUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val ongoingMoviesUseCase: OngoingMoviesUseCase,
    private val movieGenresListUseCase: MovieGenresUseCase,
    application: Application
) : BaseViewModel(application), ListAdapterClickListener<OngoingMovieUiModel> {

    val ongoingMoviesList: MutableLiveData<List<OngoingMovieUiModel>> = MutableLiveData()
    private val genres = hashMapOf<Int, String>()

    fun getMovieInformationWithExpression() = viewModelScope.launch {
        ongoingMoviesUseCase.run(UseCase.None).either(::handleFailure, ::handleOngoingMoviesList)
    }

    fun getMovieGenres() = viewModelScope.launch {
        movieGenresListUseCase.run(UseCase.None).either(::handleFailure, ::handleMovieGenres)
    }

    private fun handleOngoingMoviesList(list: GetOngoingMoviesResponseModel?) {
        list?.results?.let { results ->
            if (results.isNotEmpty()) {
                ongoingMoviesList.postValue(results.map { movie ->
                    movie.toUiModel(ROUNDED_RADIUS, getGenresAsString(movie))
                })
            }
        }
    }

    private fun handleMovieGenres(list: MovieGenresResponseModel?) {
        list?.genres?.forEach { genre ->
            genres[genre.id ?: 0] = genre.name ?: ""
        }
    }

    private fun getGenresAsString(movie: Result?): String? {
        return movie?.genreIds?.map { id -> genres[id] }?.joinToString(COMMA_SEPARATOR)
    }

    override fun onAdapterItemClicked(uiModel: OngoingMovieUiModel) {
    }
}