package com.example.imdb.scene.movies

import androidx.lifecycle.Observer
import com.example.imdb.R
import com.example.imdb.base.BaseFragment
import com.example.imdb.databinding.FragmentMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<MoviesViewModel, FragmentMoviesBinding>(
    R.layout.fragment_movies
) {

    override fun observeData() {
        super.observeData()

        viewModel.movieInfo.observe(
            viewLifecycleOwner,
            Observer {

            }
        )
    }

    override fun initialize() {
        super.initialize()
        viewModel.getMovieInformationWithExpression()
    }
}