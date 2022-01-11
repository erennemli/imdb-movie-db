package com.example.imdb.util.general

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.util.general.Constants.Common.ZERO

class DividerDecoration(val orientation: Int, val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (orientation == RecyclerView.HORIZONTAL) {
            with(outRect) {
                left = space
                right = space
                top = ZERO
                bottom = ZERO
            }
        } else {
            with(outRect) {
                left = ZERO
                right = ZERO
                top = space
                bottom = space
            }
        }
    }
}