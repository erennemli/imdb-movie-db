package com.example.imdb.scene.series

import com.example.imdb.R
import com.example.imdb.base.BaseFragment
import com.example.imdb.databinding.FragmentSeriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeriesFragment : BaseFragment<SeriesViewModel, FragmentSeriesBinding>(
    R.layout.fragment_series
)