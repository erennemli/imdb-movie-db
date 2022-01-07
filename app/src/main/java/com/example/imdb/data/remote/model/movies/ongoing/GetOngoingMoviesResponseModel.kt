package com.example.imdb.data.remote.model.movies.ongoing

import androidx.annotation.Keep

@Keep
data class GetOngoingMoviesResponseModel(
    val errorMessage: String?,
    val items: List<Item>?
)

@Keep
data class Item(
    val contentRating: String?,
    val directorList: List<Director>?,
    val directors: String?,
    val fullTitle: String?,
    val genreList: List<Genre>?,
    val genres: String?,
    val id: String?,
    val imDbRating: String?,
    val imDbRatingCount: String?,
    val image: String?,
    val metacriticRating: String?,
    val plot: String?,
    val releaseState: String?,
    val runtimeMins: String?,
    val runtimeStr: String?,
    val starList: List<Star>?,
    val stars: String?,
    val title: String?,
    val year: String?
)

@Keep
data class Director(
    val id: String?,
    val name: String?
)

@Keep
data class Genre(
    val key: String?,
    val value: String?
)

@Keep
data class Star(
    val id: String?,
    val name: String?
)