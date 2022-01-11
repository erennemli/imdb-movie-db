package com.example.imdb.data.remote.api

import com.example.imdb.BuildConfig
import com.example.imdb.data.remote.model.movies.genre.MovieGenresResponseModel
import com.example.imdb.data.remote.model.movies.ongoing.GetOngoingMoviesResponseModel
import com.example.imdb.data.remote.model.movies.popular.GetPopularMoviesResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET(ONGOING_MOVIES)
    suspend fun getOngoingMovies(
        @Query(API_KEY_STRING) apiKey: String? = BuildConfig.API_KEY
    ): GetOngoingMoviesResponseModel?

    @GET(MOVIE_GENRES)
    suspend fun getMovieGenres(
        @Query(API_KEY_STRING) apiKey: String? = BuildConfig.API_KEY
    ): MovieGenresResponseModel?

    @GET(POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query(API_KEY_STRING) apiKey: String? = BuildConfig.API_KEY
    ): GetPopularMoviesResponseModel?

    companion object {
        private const val API_KEY_STRING = "api_key"
        private const val ONGOING_MOVIES = "movie/now_playing"
        private const val MOVIE_GENRES = "genre/movie/list"
        private const val POPULAR_MOVIES = "movie/popular"
    }
}