package com.example.imdb.data.repository

import com.example.imdb.data.remote.datasource.SearchRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val searchRemoteDataSource: SearchRemoteDataSource
) {

    suspend fun searchMulti(query: String?) =
        searchRemoteDataSource.searchMulti(query = query)
}