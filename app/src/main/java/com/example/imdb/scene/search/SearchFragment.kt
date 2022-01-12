package com.example.imdb.scene.search

import com.example.imdb.R
import com.example.imdb.base.BaseFragment
import com.example.imdb.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(
    R.layout.fragment_search
) {
    override fun initialize() {
        super.initialize()

        binder.searchComponent.apply {
            initializedSearchView(requireActivity())

            onQueryTextChange = { query ->
            }
        }
    }
}