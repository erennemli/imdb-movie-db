package com.example.imdb.scene.series

import com.example.imdb.R
import com.example.imdb.adapter.TopRatedSeriesAdapter
import com.example.imdb.base.BaseFragment
import com.example.imdb.databinding.FragmentSeriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment : BaseFragment<SeriesViewModel, FragmentSeriesBinding>(
    R.layout.fragment_series
) {
    override fun initialize() {
        super.initialize()
        with(binder) {
            viewModel = this@SeriesFragment.viewModel
            adapterTopRated = TopRatedSeriesAdapter(this@SeriesFragment.viewModel)
        }

        with(viewModel) {
            getSeriesGenres()
            getTopRatedSeries()
            getPopularSeries()
        }
    }
}