package com.efedaniel.ulesson.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.efedaniel.ulesson.R

@BindingAdapter("coilImage")
fun ImageView.loadCoilImage(url: String?) {
    url?.let {
        load(it) {
            crossfade(true)
            placeholder(R.color.placeholder)
        }
    }
}
