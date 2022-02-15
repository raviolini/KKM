package com.example.kkm.binding

import android.app.DownloadManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import com.example.kkm.R

@BindingAdapter("imageUrl")
fun imageKost(imageView : ImageView, img : String)=
    Glide.with(imageView.context)
        .load(img)
        .apply(RequestOptions.circleCropTransform().placeholder(R.drawable.ravioli))
        .into(imageView)