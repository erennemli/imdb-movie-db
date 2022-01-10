package com.example.imdb.util.databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.base.BaseAdapter
import com.example.imdb.util.general.ListAdapterItem

@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: BaseAdapter<ViewDataBinding, ListAdapterItem>?) {
    adapter?.let {
        view.adapter = it
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("submitList")
fun submitList(view: RecyclerView, list: List<ListAdapterItem>?) {
    val adapter = view.adapter as BaseAdapter<ViewDataBinding, ListAdapterItem>?
    adapter?.submitList(list?.let { ArrayList(it) })
}