package com.example.imdb.scene.search

import com.example.imdb.R
import com.example.imdb.adapter.SearchAdapter
import com.example.imdb.base.BaseFragment
import com.example.imdb.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(
    R.layout.fragment_search
) {
    override fun initialize() {
        super.initialize()

        with(binder) {
            viewModel = this@SearchFragment.viewModel
            adapterSearch = SearchAdapter(this@SearchFragment.viewModel)

            searchComponent.apply {
                initializedSearchView(requireActivity())

                onQueryTextChange = { query ->
                    this@SearchFragment.viewModel.searchMulti(query)
                }
            }
        }
    }
}