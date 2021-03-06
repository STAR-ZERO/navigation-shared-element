package com.example.withoutnavigation.sharedelementsample.uitl

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("imageURL")
fun ImageView.setImageURL(url: String?) {
    if (url == null) {
        setImageDrawable(null)
        return
    }

    GlideApp.with(this)
            .load(url)
            .into(this)
}