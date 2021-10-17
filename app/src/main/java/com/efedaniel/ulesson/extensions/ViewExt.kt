package com.efedaniel.ulesson.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Spinner
import androidx.annotation.LayoutRes
import androidx.core.widget.NestedScrollView
import com.efedaniel.ulesson.common.customviews.AutoScrollViewPager

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

fun Spinner.onSelectedIndexChanged(listener: (Int) -> Unit) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            listener(position)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }
}

fun AutoScrollViewPager.setupDefaultState() {
    setCycle(true)
    setAutoScrollDurationFactor(2.0)
    isStopScrollWhenTouch = true
    interval = 5000
    pageMargin = 20
    setPadding(20, 0, 20, 0)
}
