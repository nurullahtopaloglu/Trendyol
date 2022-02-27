package com.example.trendyol.ui.util

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.trendyol.model.Widgets

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()
fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

val View.lifecycleOwner get() = ViewTreeLifecycleOwner.get(this)

fun ImageView.loadImage(imageUrl: String) {
    val imageView = this
    Glide.with(imageView.context)
        .asBitmap()
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                imageView.setImageBitmap(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        })
}

fun CardView.setCardConfig(model: Widgets) {
    val displayMetrics = Resources.getSystem().displayMetrics
    val deviceWidth = displayMetrics.widthPixels
    val deviceHeight = displayMetrics.heightPixels

    val cardWidth = ViewGroup.LayoutParams.MATCH_PARENT
    val cardHeight = (deviceWidth * model.height) / model.width

    val marginVertical = model.displayOptions.paddingTopBottom.toPx()
    val marginHorizontal = model.displayOptions.paddingRightLeft.toPx()

    val layoutParams = FrameLayout.LayoutParams(cardWidth, cardHeight).apply {
        setMargins(marginVertical, marginHorizontal, marginVertical, marginHorizontal)
    }

    this. layoutParams = layoutParams

    if (marginVertical != 0) {
        this.radius = 16.toPx().toFloat()
    }
}

fun CardView.setSliderCardConfig(model: Widgets, position: Int) {
    val displayMetrics = Resources.getSystem().displayMetrics
    val deviceWidth = displayMetrics.widthPixels
    val deviceHeight = displayMetrics.heightPixels

    val cardWidth = (deviceWidth / 2.7).toInt()
    var cardHeight = (deviceWidth * model.height) / model.width

    if (model.width == 0 || model.height == 0) {
        cardHeight = ViewGroup.LayoutParams.WRAP_CONTENT
    }

    val marginVertical = model.displayOptions.paddingTopBottom.toPx()
    val marginHorizontal = model.displayOptions.paddingRightLeft.toPx()

    val layoutParams = FrameLayout.LayoutParams(cardWidth, cardWidth)
    if (position == 0) { // first element left margin fix
        layoutParams.setMargins(marginHorizontal, marginVertical, marginHorizontal/2, marginVertical)
    } else if (position == model.bannerContents.size -1) { // last element right margin fix
        layoutParams.setMargins(marginHorizontal/2, marginVertical, marginHorizontal, marginVertical)
    } else {
        layoutParams.setMargins(marginHorizontal/2, marginVertical, marginHorizontal/2, marginVertical)
    }

    this. layoutParams = layoutParams

    if (marginVertical != 0) {
        this.radius = 16.toPx().toFloat()
    }
}

fun CardView.setListingCard(model: Widgets) {
    val marginVertical = model.displayOptions.paddingTopBottom.toPx()
    val marginHorizontal = model.displayOptions.paddingRightLeft.toPx()

    val lp = this.layoutParams as GridLayoutManager.LayoutParams
    lp.setMargins(marginHorizontal, marginVertical, marginHorizontal, marginVertical)

    this. layoutParams = lp

    if (marginVertical != 0) {
        this.radius = 16.toPx().toFloat()
    }
}