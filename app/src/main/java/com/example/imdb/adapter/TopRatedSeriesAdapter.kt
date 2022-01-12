package com.example.imdb.adapter

import androidx.databinding.ViewDataBinding
import com.example.imdb.R
import com.example.imdb.base.BaseAdapter
import com.example.imdb.databinding.ItemTopRatedSeriesBinding
import com.example.imdb.util.general.ListAdapterItem
import com.example.imdb.util.listener.ListAdapterClickListener
import com.example.imdb.view.uimodel.TopRatedSeriesUiModel

class TopRatedSeriesAdapter(
    private val listItemSelectionClickListener: ListAdapterClickListener<ListAdapterItem>
) : BaseAdapter<ViewDataBinding, TopRatedSeriesUiModel>() {

    override fun getLayoutId(position: Int): Int = R.layout.item_top_rated_series

    override fun bind(binding: ViewDataBinding, item: TopRatedSeriesUiModel, position: Int) {
        if (binding is ItemTopRatedSeriesBinding) {
            with(binding) {
                uiModel = item
                root.setOnClickListener {
                    listItemSelectionClickListener.onAdapterItemClicked(item)
                }
            }
        }
    }

}