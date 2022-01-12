package com.example.imdb.domain

import com.example.imdb.data.remote.model.search.multi.SearchMultiResponseModel
import com.example.imdb.data.repository.SearchRepository
import com.example.imdb.util.general.UseCase
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SearchMultiUseCase @Inject constructor(
    dispatcher: CoroutineDispatcher,
    private val searchRepository: SearchRepository
) : UseCase<SearchMultiResponseModel?, SearchMultiUseCase.Params>(dispatcher) {

    override suspend fun buildUseCase(params: Params): SearchMultiResponseModel? =
        searchRepository.searchMulti(query = params.query)

    data class Params(val query: String?)
}