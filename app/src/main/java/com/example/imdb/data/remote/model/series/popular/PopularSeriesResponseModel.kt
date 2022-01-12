package com.example.imdb.data.remote.model.series.popular

import androidx.annotation.Keep
import com.example.imdb.view.uimodel.PopularSeriesUiModel
import com.squareup.moshi.Json

@Keep
data class PopularSeriesResponseModel(
    @Json(name = "page") val page: Int?,
    @Json(name = "results") val resultPopularSeries: List<ResultPopularSeries>?,
    @Json(name = "total_pages") val totalPages: Int?,
    @Json(name = "total_results") val totalResults: Int?
)

data class ResultPopularSeries(
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "first_air_date") val firstAirDate: String?,
    @Json(name = "genre_ids") val genreIds: List<Int>?,
    @Json(name = "id") val id: Int?,
    @Json(name = "name")  val name: String?,
    @Json(name = "origin_country") val originCountry: List<String>?,
    @Json(name = "original_language") val originalLanguage: String?,
    @Json(name = "original_name") val originalName: String?,
    @Json(name = "overview") val overview: String?,
    @Json(name = "popularity") val popularity: Double?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "vote_average") val voteAverage: Double?,
    @Json(name = "vote_count") val voteCount: Int?
) {
    fun toUiModel(radius: Int?, genres: String?) = PopularSeriesUiModel(
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        genreIds = genreIds,
        id = id,
        name = name,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount,
        imageRoundedRadius = radius,
        genresString = genres
    )
}