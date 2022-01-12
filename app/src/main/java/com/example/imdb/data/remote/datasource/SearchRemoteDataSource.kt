package com.example.imdb.data.remote.datasource

import com.example.imdb.base.BaseRemoteDataSource
import com.example.imdb.data.remote.api.SearchService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRemoteDataSource @Inject constructor(
    private val searchService: SearchService
) : BaseRemoteDataSource() {

    suspend fun searchMulti(query: String?) = invoke {
        searchService.searchMulti(query = query)
    }

}