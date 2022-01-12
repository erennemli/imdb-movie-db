package com.example.imdb.domain

import com.example.imdb.data.remote.model.series.popular.PopularSeriesResponseModel
import com.example.imdb.data.repository.SeriesRepository
import com.example.imdb.util.general.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class PopularSeriesUseCase @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val seriesRepository: SeriesRepository
) : UseCase<PopularSeriesResponseModel?, UseCase.None>(dispatcher) {

    override suspend fun buildUseCase(params: None): PopularSeriesResponseModel? =
        seriesRepository.getPopularSeries()
}