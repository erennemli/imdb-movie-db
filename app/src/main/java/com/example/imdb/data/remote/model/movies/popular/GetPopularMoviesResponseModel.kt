package com.example.imdb.data.remote.model.movies.popular

import androidx.annotation.Keep
import com.example.imdb.view.uimodel.PopularMoviesUiModel
import com.squareup.moshi.Json

@Keep
data class GetPopularMoviesResponseModel(
    @Json(name = "page") val page: Int?,
    @Json(name = "results") val resultPopularMovies: List<ResultPopularMovies>?,
    @Json(name = "total_pages") val totalPages: Int?,
    @Json(name = "total_results") val totalResults: Int?
)

@Keep
data class ResultPopularMovies(
    @Json(name = "adult") val adult: Boolean?,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "genre_ids") val genreIds: List<Int>?,
    @Json(name = "id") val id: Int?,
    @Json(name = "original_language") val originalLanguage: String?,
    @Json(name = "original_title") val originalTitle: String?,
    @Json(name = "overview") val overview: String?,
    @Json(name = "popularity") val popularity: Double?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "release_date") val releaseDate: String?,
    @Json(name = "title") val title: String?,
    @Json(name = "video") val video: Boolean?,
    @Json(name = "vote_average") val voteAverage: Double?,
    @Json(name = "vote_count") val voteCount: Int?
) {
    fun toUiModel(radius: Int?, genres: String?) = PopularMoviesUiModel(
        adult = adult,
        backdropPath = backdropPath,
        genreIds = genreIds,
        id = id,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        imageRoundedRadius = radius,
        genresString = genres
    )
}