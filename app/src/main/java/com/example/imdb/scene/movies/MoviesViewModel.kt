package com.example.imdb.scene.movies

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imdb.base.BaseViewModel
import com.example.imdb.data.remote.model.movies.ongoing.GetOngoingMoviesResponseModel
import com.example.imdb.domain.MoviesUseCase
import com.example.imdb.util.general.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesUseCase: MoviesUseCase,
    application: Application
) : BaseViewModel(application) {

    val movieInfo: MutableLiveData<GetOngoingMoviesResponseModel> = MutableLiveData()

    fun getMovieInformationWithExpression() = viewModelScope.launch {
        movieInfo.postValue(moviesUseCase.run(UseCase.None)
            .getRightHandleLeft {  })
    }
}