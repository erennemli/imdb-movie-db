package com.example.imdb.util.extension

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.annotation.ColorInt

fun View.setRoundedBackground(
    radius: Float,
    @ColorInt color: Int,
    stroke: Int? = null,
    @ColorInt strokeColor: Int? = Color.WHITE
) {
    addOnLayoutChangeListener(object : View.OnLayoutChangeListener {
        override fun onLayoutChange(
            v: View?,
            left: Int,
            top: Int,
            right: Int,
            bottom: Int,
            oldLeft: Int,
            oldTop: Int,
            oldRight: Int,
            oldBottom: Int
        ) {
            val shape = GradientDrawable()
            shape.apply {
                cornerRadius = measuredHeight / radius
                setColor(color)
                safeLet(stroke, strokeColor) { p1, p2 ->
                    setStroke(p1, p2)
                }
            }
            background = shape
            removeOnLayoutChangeListener(this)
        }
    })
}