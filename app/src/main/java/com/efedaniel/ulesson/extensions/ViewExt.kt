package com.efedaniel.ulesson.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

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
