package com.example.imdb.data.remote.model.search.multi

import com.example.imdb.view.uimodel.SearchMultiUiModel
import com.squareup.moshi.Json

data class SearchMultiResponseModel(
    @Json(name ="page") val page: Int?,
    @Json(name ="results") val resultSearchMulti: List<ResultSearchMulti>?,
    @Json(name ="total_pages") val totalPages: Int?,
    @Json(name ="total_results") val totalResults: Int?
)

data class ResultSearchMulti(
    @Json(name ="adult") val adult: Boolean?,
    @Json(name ="backdrop_path") val backdropPath: String?,
    @Json(name ="first_air_date") val firstAirDate: String?,
    @Json(name ="gender") val gender: Int?,
    @Json(name ="genre_ids") val genreIds: List<Int>?,
    @Json(name ="id") val id: Int?,
    @Json(name ="known_for") val knownFor: List<KnownForSearchMulti>?,
    @Json(name ="known_for_department") val knownForDepartment: String?,
    @Json(name ="media_type") val mediaType: String?,
    @Json(name ="name") val name: String?,
    @Json(name ="origin_country") val originCountry: List<String>?,
    @Json(name ="original_language") val originalLanguage: String?,
    @Json(name ="original_name") val originalName: String?,
    @Json(name ="original_title") val originalTitle: String?,
    @Json(name ="overview") val overview: String?,
    @Json(name ="popularity") val popularity: Double?,
    @Json(name ="poster_path") val posterPath: String?,
    @Json(name ="profile_path") val profilePath: Any?,
    @Json(name ="release_date") val releaseDate: String?,
    @Json(name ="title") val title: String?,
    @Json(name ="video") val video: Boolean?,
    @Json(name ="vote_average") val voteAverage: Double?,
    @Json(name ="vote_count") val voteCount: Int?
) {
    fun toUiModel(imageRadius: Int?) = SearchMultiUiModel(
        adult = adult,
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        gender = gender,
        genreIds = genreIds,
        id = id,
        knownFor = knownFor,
        knownForDepartment = knownForDepartment,
        mediaType = mediaType,
        name = name,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        profilePath = profilePath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        imageRadius = imageRadius
    )
}

data class KnownForSearchMulti(
    @Json(name ="adult") val adult: Boolean?,
    @Json(name ="genre_ids") val genre_ids: List<Int>?,
    @Json(name ="id") val id: Int?,
    @Json(name ="media_type") val media_type: String?,
    @Json(name ="original_language") val original_language: String?,
    @Json(name ="original_title") val original_title: String?,
    @Json(name ="overview") val overview: String?,
    @Json(name ="poster_path") val poster_path: String?,
    @Json(name ="release_date") val release_date: String?,
    @Json(name ="title") val title: String?,
    @Json(name ="video") val video: Boolean?,
    @Json(name ="vote_average") val vote_average: Int?,
    @Json(name ="vote_count") val vote_count: Int?
)