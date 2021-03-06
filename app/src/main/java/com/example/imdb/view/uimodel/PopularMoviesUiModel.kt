package com.example.imdb.view.uimodel

import androidx.annotation.Keep
import com.example.imdb.util.general.ListAdapterItem

@Keep
data class PopularMoviesUiModel(
    val adult: Boolean?,
    val backdropPath: String?,
    val genreIds: List<Int>?,
    override val id: Int? = 0,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val imageRoundedRadius: Int?,
    var genresString: String? = null
) : ListAdapterItem