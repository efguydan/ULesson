package com.efedaniel.ulesson.utils

import android.content.res.ColorStateList
import android.view.View
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
        text = DateUtils.getRelativeDate(time)
    }
}

@BindingAdapter("subjectName")
fun TextView.setSubjectName(name: String?) {
    name?.let {
        text = it
        setTextColor(
            ContextCompat.getColor(
                context,
                when (it.toLowerCase()) {
                    Constants.Data.SUBJECTS[1].toLowerCase() -> R.color.mathematics
                    Constants.Data.SUBJECTS[2].toLowerCase() -> R.color.english
                    Constants.Data.SUBJECTS[3].toLowerCase() -> R.color.chemistry
                    Constants.Data.SUBJECTS[4].toLowerCase() -> R.color.biology
                    Constants.Data.SUBJECTS[5].toLowerCase() -> R.color.physics
                    else -> R.color.text_color
                }
            )
        )
    }
}

@BindingAdapter(
    requireAll = false,
    value = ["visible", "occupying"]
)
fun setVisibility(view: View, visible: Boolean, occupying: Boolean = false) {

    // Load resource based on occupying state.
    val res = when (occupying) {
        true -> View.INVISIBLE
        false -> View.GONE
    }

    // Update view visibility.
    view.visibility = if (visible) View.VISIBLE else res
}
