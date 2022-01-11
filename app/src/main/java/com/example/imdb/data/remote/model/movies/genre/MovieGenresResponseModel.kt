package com.example.imdb.data.remote.model.movies.genre

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class MovieGenresResponseModel(
    @Json(name = "genres") val genres: List<Genre>?
)

@Keep
data class Genre(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?
)