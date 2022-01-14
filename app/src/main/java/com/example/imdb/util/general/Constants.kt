package com.example.imdb.util.general

object Constants {
    object Common {
        const val ZERO = 0
        const val ROUNDED_RADIUS = 16
        const val COMMA_SEPARATOR = ", "
        const val EMPTY = ""
        const val INVALID_RES = -1
        const val MAX_INPUT_LENGTH = 256
        const val DOT = '\u2B24'
    }

    object Network {
        const val API_KEY_STRING = "api_key"
    }

    object Search {
        const val MOVIE = "movie"
        const val SERIES = "tv"
        const val CAST = "person"
        const val QUERY_MIN_LENGTH = 2
        const val SEARCH_INTERVAL = 300L
    }
}