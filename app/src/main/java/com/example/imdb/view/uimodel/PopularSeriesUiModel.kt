package com.example.imdb.view.uimodel

import androidx.annotation.Keep
import com.example.imdb.util.general.ListAdapterItem

@Keep
data class PopularSeriesUiModel(
    val backdropPath: String?,
    val firstAirDate: String?,
    val genreIds: List<Int>?,
    override val id: Int?,
    val name: String?,
    val originCountry: List<String>?,
    val originalLanguage: String?,
    val originalName: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val imageRoundedRadius: Int?,
    var genresString: String? = null
) : ListAdapterItem