package com.example.imdb.util.listener

import com.example.imdb.util.general.ListAdapterItem

interface ListAdapterClickListener<T : ListAdapterItem> {
    fun onAdapterItemClicked(uiModel: T)
}