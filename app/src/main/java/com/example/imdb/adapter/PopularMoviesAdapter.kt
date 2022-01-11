package com.example.imdb.adapter

import androidx.databinding.ViewDataBinding
import com.example.imdb.R
import com.example.imdb.base.BaseAdapter
import com.example.imdb.databinding.ItemPopularMoviesBinding
import com.example.imdb.util.general.ListAdapterItem
import com.example.imdb.util.listener.ListAdapterClickListener
import com.example.imdb.view.uimodel.PopularMoviesUiModel

class PopularMoviesAdapter(
    private val listItemSelectionClickListener: ListAdapterClickListener<ListAdapterItem>
) : BaseAdapter<ViewDataBinding, PopularMoviesUiModel>() {

    override fun getLayoutId(position: Int): Int = R.layout.item_popular_movies

    override fun bind(binding: ViewDataBinding, item: PopularMoviesUiModel, position: Int) {
        if (binding is ItemPopularMoviesBinding) {
            with(binding) {
                uiModel = item
                root.setOnClickListener {
                    listItemSelectionClickListener.onAdapterItemClicked(item)
                }
            }
        }
    }
}