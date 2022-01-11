package com.example.imdb.util.databinding

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.BuildConfig
import com.example.imdb.base.BaseAdapter
import com.example.imdb.util.extension.loadImage
import com.example.imdb.util.extension.loadRoundedImage
import com.example.imdb.util.extension.loadSpecificCornerRoundedImage
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

@BindingAdapter("imageFromUrl", "placeHolderRes", "errorRes", "circleImage", requireAll = false)
fun setImage(
    view: ImageView,
    url: String?,
    @DrawableRes placeholderRes: Int?,
    @DrawableRes errorRes: Int?,
    circleImage: Boolean = false
) {
    val defaultDrawable = android.R.drawable.stat_notify_error

    view.loadImage(
        url = BuildConfig.IMAGE_URL + url,
        placeholderRes = placeholderRes ?: defaultDrawable,
        errorRes = errorRes ?: defaultDrawable,
        isCircle = circleImage
    )
}

@BindingAdapter("roundedImageFromUrl", "placeHolderRes", "errorRes", "radius", requireAll = false)
fun setRoundedImage(
    view: ImageView,
    url: String?,
    @DrawableRes placeholderRes: Int?,
    @DrawableRes errorRes: Int?,
    radius: Int?
) {
    val defaultDrawable = android.R.drawable.stat_notify_error

    view.loadRoundedImage(
        url = BuildConfig.IMAGE_URL + url,
        placeholderRes = placeholderRes ?: defaultDrawable,
        errorRes = errorRes ?: defaultDrawable,
        radius = radius
    )
}

@BindingAdapter("imageSpecificCornerFromUrl", "bottomLeft", "bottomRight", "topLeft", "topRight", "placeHolderRes", "errorRes", "radius", requireAll = false)
fun setSpecificCornerRoundedImage(
    view: ImageView,
    url: String?,
    bottomLeft: Float = 0f,
    bottomRight: Float = 0f,
    topLeft: Float = 0f,
    topRight: Float = 0f,
    @DrawableRes placeholderRes: Int?,
    @DrawableRes errorRes: Int?,
    radius: Int?
) {
    val defaultDrawable = android.R.drawable.stat_notify_error

    view.loadSpecificCornerRoundedImage(
        url = BuildConfig.IMAGE_URL + url,
        bottomLeft = bottomLeft,
        bottomRight = bottomRight,
        topLeft = topLeft,
        topRight = topRight,
        placeholderRes = placeholderRes ?: defaultDrawable,
        errorRes = errorRes ?: defaultDrawable,
        radius = radius
    )
}