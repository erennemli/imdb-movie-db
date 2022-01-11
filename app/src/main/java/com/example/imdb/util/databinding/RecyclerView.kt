package com.example.imdb.util.databinding

import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.ListenerUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.R
import com.example.imdb.util.general.DividerDecoration

@BindingAdapter("dividerSize")
fun RecyclerView.setDivider(space: Float) {
    val layoutManager = this.layoutManager
    if (layoutManager == null || layoutManager !is LinearLayoutManager) return

    var divider = ListenerUtil.getListener<DividerDecoration>(this, R.id.recyclerViewDivider)

    divider?.let {
        if (divider.orientation == layoutManager.orientation && divider.space == space.toInt()) return

        removeItemDecoration(divider)
        divider = DividerDecoration(divider.orientation, space.toInt())
        ListenerUtil.trackListener(this, divider, R.id.recyclerViewDivider)
    } ?: run {
        divider = DividerDecoration(layoutManager.orientation, space.toInt())
        ListenerUtil.trackListener(this, divider, R.id.recyclerViewDivider)
    }

    addItemDecoration(divider)
}

@BindingAdapter("paging")
fun RecyclerView.setPaging(enabled: Boolean) {
    val oldHelper = ListenerUtil.getListener<PagerSnapHelper>(this, R.id.recyclerViewSnapHelper)

    if (enabled && oldHelper == null) {
        val snapHelper = PagerSnapHelper()
        ListenerUtil.trackListener(this, snapHelper, R.id.recyclerViewSnapHelper)
        snapHelper.attachToRecyclerView(this)
    } else if (enabled.not() && oldHelper != null) {
        oldHelper.attachToRecyclerView(null)
    }
}