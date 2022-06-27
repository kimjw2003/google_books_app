package com.example.wanted.custom.bindingadapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.wanted.R


@BindingAdapter("item_book_image")
fun itemBookImage(view: ImageView, image: String?) {
    Glide.with(view)
        .load(image.toString())
        .error(R.drawable.default_img)
        .into(view)
}

@BindingAdapter("item_book_publisher")
fun itemBookPublisher(view: TextView, publisher: String?) {
    view.text = publisher ?: ""
}