package com.example.imdb.util.extension

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

fun Context.loadColor(@ColorRes res: Int?): Int {
    return res?.let {
        try {
            ContextCompat.getColor(this, it)
        } catch (e: Resources.NotFoundException) {
            Color.BLACK
        }
    } ?: Color.BLACK
}

fun Context.loadDrawable(@DrawableRes res: Int?): Drawable? {
    return res?.let {
        try {
            ContextCompat.getDrawable(this, it)
        } catch (e: Resources.NotFoundException) {
            null
        }
    }
}