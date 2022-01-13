package com.example.imdb.data.remote.api

import com.example.imdb.BuildConfig
import com.example.imdb.data.remote.model.search.multi.SearchMultiResponseModel
import com.example.imdb.util.general.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {

    @GET(SEARCH_MULTI)
    suspend fun searchMulti(
        @Query(Constants.Network.API_KEY_STRING) apiKey: String? = BuildConfig.API_KEY,
        @Query(QUERY) query: String?
    ): SearchMultiResponseModel?

    companion object {
        private const val SEARCH_MULTI = "search/multi"
        private const val QUERY = "query"
    }
}