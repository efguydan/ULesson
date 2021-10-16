package com.efedaniel.ulesson.utils

import android.content.res.ColorStateList
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.efedaniel.ulesson.R

@BindingAdapter("coilImage")
fun ImageView.loadCoilImage(url: String?) {
    url?.let {
        load(it) {
            crossfade(true)
            transformations(RoundedCornersTransformation(16F))
            placeholder(R.color.placeholder)
        }
    }
}

@BindingAdapter("lessonStatus")
fun TextView.setLessonStatus(status: String?) {
    status?.let {
        text = status

        when (status) {
            Constants.Keys.LIVE -> {
                setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_bytesize_bell, 0, 0, 0)
                backgroundTintList = ColorStateList
                    .valueOf(ContextCompat.getColor(context, R.color.red))
            }
            Constants.Keys.REPLAY -> {
                setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_play_small, 0, 0, 0)
                backgroundTintList = ColorStateList
                    .valueOf(ContextCompat.getColor(context, R.color.yellow))
            }
            Constants.Keys.UPCOMING -> {
                setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_calendar_small, 0, 0, 0)
                backgroundTintList = ColorStateList
                    .valueOf(ContextCompat.getColor(context, R.color.light_black))
            }
        }
    }
}

@BindingAdapter("startTime")
fun TextView.setStartTime(time: String?) {
    time?.let {
        text = time
    }
}
