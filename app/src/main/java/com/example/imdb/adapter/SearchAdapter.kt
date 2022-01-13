package com.example.imdb.adapter

import androidx.databinding.ViewDataBinding
import com.example.imdb.R
import com.example.imdb.base.BaseAdapter
import com.example.imdb.databinding.ItemSearchCastBinding
import com.example.imdb.databinding.ItemSearchMovieBinding
import com.example.imdb.databinding.ItemSearchSeriesBinding
import com.example.imdb.scene.search.SearchType
import com.example.imdb.util.general.ListAdapterItem
import com.example.imdb.util.listener.ListAdapterClickListener
import com.example.imdb.view.uimodel.SearchMultiUiModel

class SearchAdapter(
    private val listItemSelectionClickListener: ListAdapterClickListener<ListAdapterItem>
) : BaseAdapter<ViewDataBinding, SearchMultiUiModel>() {

    override fun getLayoutId(position: Int): Int = when (currentList[position].mediaTypeInt) {
        SearchType.MOVIE_TYPE.ordinal -> R.layout.item_search_movie
        SearchType.SERIES_TYPE.ordinal -> R.layout.item_search_series
        else -> R.layout.item_search_cast
    }

    override fun bind(binding: ViewDataBinding, item: SearchMultiUiModel, position: Int) {
        when (binding) {
            is ItemSearchMovieBinding -> {
                with(binding) {
                    uiModel = item
                    root.setOnClickListener {
                        listItemSelectionClickListener.onAdapterItemClicked(item)
                    }
                }
            }

            is ItemSearchSeriesBinding -> {
                with(binding) {
                    uiModel = item
                    root.setOnClickListener {
                        listItemSelectionClickListener.onAdapterItemClicked(item)
                    }
                }
            }

            is ItemSearchCastBinding -> {
                with(binding) {
                    uiModel = item
                    root.setOnClickListener {
                        listItemSelectionClickListener.onAdapterItemClicked(item)
                    }
                }
            }
        }
    }
}