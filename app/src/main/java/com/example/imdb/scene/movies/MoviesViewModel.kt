package com.example.imdb.scene.movies

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdb.base.BaseViewModel
import com.example.imdb.data.remote.model.movies.genre.MovieGenresResponseModel
import com.example.imdb.data.remote.model.movies.ongoing.GetOngoingMoviesResponseModel
import com.example.imdb.data.remote.model.movies.ongoing.Result
import com.example.imdb.data.remote.model.movies.popular.GetPopularMoviesResponseModel
import com.example.imdb.domain.MovieGenresUseCase
import com.example.imdb.domain.OngoingMoviesUseCase
import com.example.imdb.domain.PopularMoviesUseCase
import com.example.imdb.util.general.Constants.Common.COMMA_SEPARATOR
import com.example.imdb.util.general.Constants.Common.EMPTY
import com.example.imdb.util.general.Constants.Common.ROUNDED_RADIUS
import com.example.imdb.util.general.ListAdapterItem
import com.example.imdb.util.general.UseCase
import com.example.imdb.util.listener.ListAdapterClickListener
import com.example.imdb.view.uimodel.OngoingMovieUiModel
import com.example.imdb.view.uimodel.PopularMoviesUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val ongoingMoviesUseCase: OngoingMoviesUseCase,
    private val movieGenresListUseCase: MovieGenresUseCase,
    private val popularMoviesUseCase: PopularMoviesUseCase,
    application: Application
) : BaseViewModel(application), ListAdapterClickListener<ListAdapterItem> {

    val ongoingMoviesList: MutableLiveData<List<OngoingMovieUiModel>> = MutableLiveData()
    val popularMoviesList: MutableLiveData<List<PopularMoviesUiModel>> = MutableLiveData()
    private val genres = hashMapOf<Int, String>()

    fun getMovieInformationWithExpression() = viewModelScope.launch {
        ongoingMoviesUseCase.run(UseCase.None).either(::handleFailure, ::handleOngoingMoviesList)
    }

    fun getMovieGenres() = viewModelScope.launch {
        movieGenresListUseCase.run(UseCase.None).either(::handleFailure, ::handleMovieGenres)
    }

    fun getPopularMovies() = viewModelScope.launch {
        popularMoviesUseCase.run(UseCase.None).either(::handleFailure, ::handlePopularMoviesList)
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

    private fun handlePopularMoviesList(list: GetPopularMoviesResponseModel?) {
        list?.results?.let { results ->
            if (results.isNotEmpty()) {
                popularMoviesList.postValue(results.map { movie ->
                    movie.toUiModel(ROUNDED_RADIUS, getGenresAsString(movie))
                })
            }
        }
    }

    private fun <T> getGenresAsString(movie: T?): String? {
        return when (movie) {
            is Result? -> {
                movie?.genreIds?.map { id -> genres[id] }
                    ?.joinToString(COMMA_SEPARATOR)
            }

            is com.example.imdb.data.remote.model.movies.popular.Result? -> {
                movie?.genreIds?.map { id -> genres[id] }
                    ?.joinToString(COMMA_SEPARATOR)
            }

            else -> {
                EMPTY
            }
        }
    }

    override fun onAdapterItemClicked(uiModel: ListAdapterItem) {
        when (uiModel) {
            is OngoingMovieUiModel -> {
            }

            is PopularMoviesUiModel -> {
            }
        }
    }
}