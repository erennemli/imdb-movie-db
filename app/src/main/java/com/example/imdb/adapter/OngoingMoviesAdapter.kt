package com.example.imdb.adapter

import androidx.databinding.ViewDataBinding
import com.example.imdb.R
import com.example.imdb.base.BaseAdapter
import com.example.imdb.databinding.ItemOngoingMoviesBinding
import com.example.imdb.util.listener.ListAdapterClickListener
import com.example.imdb.view.uimodel.OngoingMovieUiModel

class OngoingMoviesAdapter(
    private val listItemSelectionClickListener: ListAdapterClickListener<OngoingMovieUiModel>
) : BaseAdapter<ViewDataBinding, OngoingMovieUiModel>() {

    override fun getLayoutId(position: Int): Int = R.layout.item_ongoing_movies

    override fun bind(binding: ViewDataBinding, item: OngoingMovieUiModel, position: Int) {
        if (binding is ItemOngoingMoviesBinding) {
            with(binding) {
                uiModel = item
                root.setOnClickListener {
                    listItemSelectionClickListener.onAdapterItemClicked(item)
                }
            }
        }
    }

}