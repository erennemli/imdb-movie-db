package com.example.imdb.data.remote.datasource

import com.example.imdb.base.BaseRemoteDataSource
import com.example.imdb.data.remote.api.SeriesService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SeriesRemoteDataSource @Inject constructor(
    private val seriesService: SeriesService
) : BaseRemoteDataSource() {

    suspend fun getTopRatedSeries() = invoke {
        seriesService.getTopRatedSeries()
    }

    suspend fun getSeriesGenres() = invoke {
        seriesService.getSeriesGenres()
    }
}