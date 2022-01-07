package com.example.imdb.util.general

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<out Type : Any?, in Params>(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    protected abstract suspend fun buildUseCase(params: Params): Type

    suspend fun run(params: Params): Either<Failure, Type> = withContext(dispatcher) {
        try {
            Either.Right(buildUseCase(params))
        } catch (failure: Failure) {
            Either.Left(failure)
        }
    }

    object None {
        override fun toString() = "UseCase.None"
    }
}