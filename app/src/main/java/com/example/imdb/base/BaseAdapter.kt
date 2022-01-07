package com.example.imdb.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.imdb.util.general.ListAdapterItem
import com.example.imdb.util.general.ListAdapterItemDiffCallback

abstract class BaseAdapter<VB : ViewDataBinding, T : ListAdapterItem>(
    diffCallback: DiffUtil.ItemCallback<T> = ListAdapterItemDiffCallback()
) : ListAdapter<T, BaseViewHolder<VB>>(diffCallback) {

    lateinit var binding: VB

    @LayoutRes
    protected abstract fun getLayoutId(position: Int): Int

    protected abstract fun bind(binding: VB, item: T, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<VB> {
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            viewType,
            parent,
            false
        )

        return BaseViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int = getLayoutId(position)

    override fun onBindViewHolder(holder: BaseViewHolder<VB>, position: Int) {
        bind(holder.binding, getItem(position), position)
    }
}