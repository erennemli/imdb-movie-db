package com.example.imdb.adapter

import androidx.databinding.ViewDataBinding
import com.example.imdb.R
import com.example.imdb.base.BaseAdapter
import com.example.imdb.databinding.ItemSearchBinding
import com.example.imdb.util.general.ListAdapterItem
import com.example.imdb.util.listener.ListAdapterClickListener
import com.example.imdb.view.uimodel.SearchMultiUiModel

class SearchAdapter(
    private val listItemSelectionClickListener: ListAdapterClickListener<ListAdapterItem>
) : BaseAdapter<ViewDataBinding, SearchMultiUiModel>() {

    override fun getLayoutId(position: Int): Int = R.layout.item_search

    override fun bind(binding: ViewDataBinding, item: SearchMultiUiModel, position: Int) {
        if (binding is ItemSearchBinding) {
            with(binding) {
                uiModel = item
                root.setOnClickListener {
                    listItemSelectionClickListener.onAdapterItemClicked(item)
                }
            }
        }
    }
}