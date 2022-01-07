package com.example.imdb.data.remote.api

import com.example.imdb.BuildConfig
import com.example.imdb.data.remote.model.movies.ongoing.GetOngoingMoviesResponseModel
import retrofit2.http.GET

interface MoviesService {

    @GET(IN_THEATERS)
    suspend fun getOngoingMovies(): GetOngoingMoviesResponseModel?

    companion object {
        private const val IN_THEATERS = "InTheaters/" + BuildConfig.API_KEY
    }
}