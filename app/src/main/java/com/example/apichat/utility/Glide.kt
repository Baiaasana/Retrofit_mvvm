package com.example.apichat.utility

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.apichat.R

class Glide {

    fun setImage(url: String, image: ImageView) {
        Glide
            .with(image.context)
            .load(url)
            .placeholder(R.mipmap.place_holder)
            .centerCrop()
            .into(image)
    }
}