package com.example.imdb.data.remote.datasource

import com.example.imdb.base.BaseRemoteDataSource
import com.example.imdb.data.remote.api.MoviesService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRemoteDataSource @Inject constructor(
    private val moviesService: MoviesService
) : BaseRemoteDataSource() {

    suspend fun getOngoingMovies() = invoke {
        moviesService.getOngoingMovies()
    }

    suspend fun getMovieGenres() = invoke {
        moviesService.getMovieGenres()
    }
}