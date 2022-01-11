package com.example.imdb.data.repository

import com.example.imdb.data.remote.datasource.SeriesRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeriesRepository @Inject constructor(
    private val seriesRemoteDataSource: SeriesRemoteDataSource
) {

    suspend fun getTopRatedSeries() =
        seriesRemoteDataSource.getTopRatedSeries()

    suspend fun getSeriesGenres() =
        seriesRemoteDataSource.getSeriesGenres()
}