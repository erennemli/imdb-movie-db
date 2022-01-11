package com.example.imdb.domain

import com.example.imdb.data.remote.model.movies.genre.MovieGenresResponseModel
import com.example.imdb.data.repository.MoviesRepository
import com.example.imdb.util.general.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class MovieGenresUseCase @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val moviesRepository: MoviesRepository
) : UseCase<MovieGenresResponseModel?, UseCase.None>(dispatcher) {

    override suspend fun buildUseCase(params: None): MovieGenresResponseModel? =
        moviesRepository.getMovieGenres()
}