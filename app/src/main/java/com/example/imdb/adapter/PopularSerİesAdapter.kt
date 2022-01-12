package com.example.imdb.adapter

import androidx.databinding.ViewDataBinding
import com.example.imdb.R
import com.example.imdb.base.BaseAdapter
import com.example.imdb.databinding.ItemPopularSeriesBinding
import com.example.imdb.util.general.ListAdapterItem
import com.example.imdb.util.listener.ListAdapterClickListener
import com.example.imdb.view.uimodel.PopularSeriesUiModel

class PopularSeriesAdapter(
    private val listItemSelectionClickListener: ListAdapterClickListener<ListAdapterItem>
) : BaseAdapter<ViewDataBinding, PopularSeriesUiModel>() {

    override fun getLayoutId(position: Int): Int = R.layout.item_popular_series

    override fun bind(binding: ViewDataBinding, item: PopularSeriesUiModel, position: Int) {
        if (binding is ItemPopularSeriesBinding) {
            with(binding) {
                uiModel = item
                root.setOnClickListener {
                    listItemSelectionClickListener.onAdapterItemClicked(item)
                }
            }
        }
    }
}