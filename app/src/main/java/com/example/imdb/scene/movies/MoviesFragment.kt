package com.example.imdb.scene.movies

import com.example.imdb.R
import com.example.imdb.adapter.OngoingMoviesAdapter
import com.example.imdb.base.BaseFragment
import com.example.imdb.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<MoviesViewModel, FragmentMoviesBinding>(
    R.layout.fragment_movies
) {
    override fun initialize() {
        super.initialize()
        with(binder) {
            viewModel = this@MoviesFragment.viewModel
            adapter = OngoingMoviesAdapter(this@MoviesFragment.viewModel)
        }
        viewModel.getMovieInformationWithExpression()
    }
}