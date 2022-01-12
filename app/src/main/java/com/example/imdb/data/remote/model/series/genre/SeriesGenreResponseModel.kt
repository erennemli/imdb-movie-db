package com.example.imdb.data.remote.model.series.genre

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class SeriesGenreResponseModel(
    @Json(name = "genres") val genres: List<Genre>?
)

@Keep
data class Genre(
    @Json(name = "id") val id: Int?,
    @Json(name = "name") val name: String?
)