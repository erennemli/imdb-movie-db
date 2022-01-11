package com.example.imdb.domain

import com.example.imdb.data.remote.model.movies.ongoing.GetOngoingMoviesResponseModel
import com.example.imdb.data.repository.MoviesRepository
import com.example.imdb.util.general.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class OngoingMoviesUseCase @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val moviesRepository: MoviesRepository
) : UseCase<GetOngoingMoviesResponseModel?, UseCase.None>(dispatcher) {

    override suspend fun buildUseCase(params: None): GetOngoingMoviesResponseModel? =
        moviesRepository.getOngoingMovies()
}