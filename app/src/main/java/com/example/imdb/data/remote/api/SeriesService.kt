package com.example.imdb.data.remote.api

import com.example.imdb.BuildConfig
import com.example.imdb.data.remote.model.series.genre.SeriesGenreResponseModel
import com.example.imdb.data.remote.model.series.toprated.TopRatedSeriesResponseModel
import com.example.imdb.util.general.Constants
import com.example.imdb.util.general.Constants.Network.API_KEY_STRING
import retrofit2.http.GET
import retrofit2.http.Query

interface SeriesService {

    @GET(SERIES_TOP_RATED)
    suspend fun getTopRatedSeries(
        @Query(API_KEY_STRING) apiKey: String? = BuildConfig.API_KEY
    ): TopRatedSeriesResponseModel?

    @GET(SERIES_GENRE)
    suspend fun getSeriesGenres(
        @Query(API_KEY_STRING) apiKey: String? = BuildConfig.API_KEY
    ): SeriesGenreResponseModel?

    companion object {
        private const val SERIES_TOP_RATED = "tv/top_rated"
        private const val SERIES_GENRE = "genre/tv/list"
    }
}