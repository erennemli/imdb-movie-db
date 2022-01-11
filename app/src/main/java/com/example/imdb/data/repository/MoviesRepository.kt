package com.example.imdb.data.repository

import com.example.imdb.data.remote.datasource.MoviesRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepository @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource
) {

    suspend fun getOngoingMovies() =
        moviesRemoteDataSource.getOngoingMovies()

    suspend fun getMovieGenres() =
        moviesRemoteDataSource.getMovieGenres()
}