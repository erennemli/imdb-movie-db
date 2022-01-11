package com.example.imdb.domain

import com.example.imdb.data.remote.model.series.toprated.TopRatedSeriesResponseModel
import com.example.imdb.data.repository.SeriesRepository
import com.example.imdb.util.general.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class TopRatedSeriesUseCase @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val seriesRepository: SeriesRepository
) : UseCase<TopRatedSeriesResponseModel?, UseCase.None>(dispatcher) {

    override suspend fun buildUseCase(params: None): TopRatedSeriesResponseModel? =
        seriesRepository.getTopRatedSeries()
}