package com.example.ascendcommerce.image

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

object GlideUtils {

    fun loadImage(
        context: Context,
        imageUrl: String,
        view: ImageView
    ) {
        Glide.with(context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)
    }

    fun loadImage(
        context: Context,
        imageUrl: String,
        view: ImageView,
        @DrawableRes placeHolder: Int
    ) {
        Glide.with(context)
            .load(imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(placeHolder)
            .into(view)
    }
}