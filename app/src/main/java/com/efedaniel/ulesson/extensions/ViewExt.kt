package com.efedaniel.ulesson.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.core.widget.NestedScrollView

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.setVisibilityState(state: Boolean) {
    if (state) show() else hide()
}

inline fun <reified T> ViewGroup.inflate(@LayoutRes layoutRes: Int): T {
    return LayoutInflater.from(context).inflate(layoutRes, this, false) as T
}

fun NestedScrollView.onScrollChanged(scrollListener: (Int) -> Unit) =
    setOnScrollChangeListener(
        NestedScrollView.OnScrollChangeListener { _, _, scrollY, _, _ ->
            scrollListener(scrollY)
        }
    )

fun View.invalidateElevation(scrollY: Int) {
    val expectedScrollY = measuredHeight / 2
    val maxElevation = 8f

    var calculatedElevation = (scrollY.toFloat() / expectedScrollY.toFloat()) * maxElevation
    if (calculatedElevation.isNaN()) calculatedElevation = 0f

    elevation = calculatedElevation
        .coerceAtLeast(0f)
        .coerceAtMost(maxElevation)
}
