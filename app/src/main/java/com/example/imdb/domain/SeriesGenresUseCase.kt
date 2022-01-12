package com.example.imdb.domain

import com.example.imdb.data.remote.model.series.genre.SeriesGenreResponseModel
import com.example.imdb.data.repository.SeriesRepository
import com.example.imdb.util.general.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SeriesGenresUseCase @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val seriesRepository: SeriesRepository
) : UseCase<SeriesGenreResponseModel?, UseCase.None>(dispatcher) {

    override suspend fun buildUseCase(params: None): SeriesGenreResponseModel? =
        seriesRepository.getSeriesGenres()
}