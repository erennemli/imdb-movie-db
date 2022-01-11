package com.example.imdb.util.extension

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.imdb.util.general.Constants.Common.ZERO

@SuppressLint("CheckResult")
fun ImageView.loadImage(
    url: String?,
    @DrawableRes placeholderRes: Int? = null,
    @DrawableRes errorRes: Int? = null,
    isCircle: Boolean = false
) {
    val requestOptions = RequestOptions().apply {
        placeholderRes?.let { placeholder(it) }
        errorRes?.let { error(it) }
        if (isCircle) {
            circleCrop()
        }
    }

    Glide.with(this)
        .load(url)
        .apply(requestOptions)
        .into(this)
}

@SuppressLint("CheckResult")
fun ImageView.loadRoundedImage(
    url: String?,
    @DrawableRes placeholderRes: Int? = null,
    @DrawableRes errorRes: Int? = null,
    radius: Int?
) {
    val requestOptions = RequestOptions().apply {
        placeholderRes?.let { placeholder(it) }
        errorRes?.let { error(it) }
        radius?.let { radius ->
            if (radius != ZERO) {
                transform(CenterCrop(), RoundedCorners(radius))
            }
        }
    }

    Glide.with(this)
        .load(url)
        .apply(requestOptions)
        .into(this)
}

@SuppressLint("CheckResult")
fun ImageView.loadSpecificCornerRoundedImage(
    url: String?,
    bottomLeft: Float = 0f,
    bottomRight: Float = 0f,
    topLeft: Float = 0f,
    topRight: Float = 0f,
    @DrawableRes placeholderRes: Int? = null,
    @DrawableRes errorRes: Int? = null,
    radius: Int?
) {
    val requestOptions = RequestOptions().apply {
        placeholderRes?.let { placeholder(it) }
        errorRes?.let { error(it) }
        radius?.let { radius ->
            if (radius != ZERO) {
                transform(CenterCrop(), GranularRoundedCorners(topLeft, topRight, bottomRight, bottomLeft))
            }
        }
    }

    Glide.with(this)
        .load(url)
        .apply(requestOptions)
        .into(this)
}