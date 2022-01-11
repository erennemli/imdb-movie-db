package com.example.imdb.domain

import com.example.imdb.data.remote.model.movies.popular.GetPopularMoviesResponseModel
import com.example.imdb.data.repository.MoviesRepository
import com.example.imdb.util.general.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class PopularMoviesUseCase @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val moviesRepository: MoviesRepository
) : UseCase<GetPopularMoviesResponseModel?, UseCase.None>(dispatcher) {

    override suspend fun buildUseCase(params: None): GetPopularMoviesResponseModel? =
        moviesRepository.getPopularMovies()
}