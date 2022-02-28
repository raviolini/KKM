package com.raviolini.kkm.binding


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import com.raviolini.kkm.R
import com.raviolini.kkm.misc.GlideApp

@BindingAdapter("avatar")
fun avatar(imageView : ImageView, avatar : String)=
    GlideApp.with(imageView.context)
        .load(avatar)
        .apply(circleCropTransform().placeholder(R.drawable.ravioli))
        .into(imageView)