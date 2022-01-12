package com.example.imdb.view.uimodel

import androidx.annotation.Keep
import com.example.imdb.data.remote.model.search.multi.KnownForSearchMulti
import com.example.imdb.util.general.ListAdapterItem

@Keep
data class SearchMultiUiModel(
    val adult: Boolean?,
    val backdropPath: String?,
    val firstAirDate: String?,
    val gender: Int?,
    val genreIds: List<Int>?,
    override val id: Int?,
    val knownFor: List<KnownForSearchMulti>?,
    val knownForDepartment: String?,
    val mediaType: String?,
    val name: String?,
    val originCountry: List<String>?,
    val originalLanguage: String?,
    val originalName: String?,
    val originalTitle: String?,
    val overview: String?,
    val popularity: Double?,
    val posterPath: String?,
    val profilePath: Any?,
    val releaseDate: String?,
    val title: String?,
    val video: Boolean?,
    val voteAverage: Double?,
    val voteCount: Int?,
    val imageRadius: Int? = null
) : ListAdapterItem