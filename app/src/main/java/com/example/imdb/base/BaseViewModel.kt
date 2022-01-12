package com.example.imdb.base

import android.app.Application
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import com.example.imdb.data.remote.model.movies.ongoing.ResultOngoingMovies
import com.example.imdb.data.remote.model.movies.popular.ResultPopularMovies
import com.example.imdb.data.remote.model.series.popular.ResultPopularSeries
import com.example.imdb.data.remote.model.series.toprated.ResultTopRatedSeries
import com.example.imdb.util.general.Constants.Common.COMMA_SEPARATOR
import com.example.imdb.util.general.Constants.Common.EMPTY
import com.example.imdb.util.general.Event
import com.example.imdb.util.general.Failure
import com.example.imdb.util.navigation.NavigationCommand

abstract class BaseViewModel(application: Application): AndroidViewModel(application) {

    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> get() = _navigation

    val genres = hashMapOf<Int, String>()

    fun navigate(
        @IdRes resId: Int,
        args: Bundle? = null,
        navOptions: NavOptions? = null,
        navigatorExtras: Navigator.Extras? = null
    ) {
        _navigation.value =
            Event(NavigationCommand.ToDirectionId(resId, args, navOptions, navigatorExtras))
    }

    fun navigate(directions: NavDirections) {
        _navigation.value = Event(NavigationCommand.ToDirection(directions))
    }

    fun navigateBack() {
        _navigation.value = Event(NavigationCommand.Back)
    }

    protected open fun handleFailure(failure: Failure) {

    }

    fun <T> getGenresAsString(model: T?): String? {
        return when (model) {
            is ResultOngoingMovies? -> {
                model?.genreIds?.map { id -> genres[id] }
                    ?.joinToString(COMMA_SEPARATOR)
            }

            is ResultPopularMovies? -> {
                model?.genreIds?.map { id -> genres[id] }
                    ?.joinToString(COMMA_SEPARATOR)
            }

            is ResultTopRatedSeries? -> {
                model?.genreIds?.map { id -> genres[id] }
                    ?.joinToString(COMMA_SEPARATOR)
            }

            is ResultPopularSeries? -> {
                model?.genreIds?.map { id -> genres[id] }
                    ?.joinToString(COMMA_SEPARATOR)
            }

            else -> {
                EMPTY
            }
        }
    }
}