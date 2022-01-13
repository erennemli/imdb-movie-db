package com.example.imdb.scene.search

import com.example.imdb.util.general.Constants.Search.CAST
import com.example.imdb.util.general.Constants.Search.MOVIE
import com.example.imdb.util.general.Constants.Search.SERIES

enum class SearchType(val title: String) {
    MOVIE_TYPE(MOVIE),
    SERIES_TYPE(SERIES),
    CAST_TYPE(CAST)
}