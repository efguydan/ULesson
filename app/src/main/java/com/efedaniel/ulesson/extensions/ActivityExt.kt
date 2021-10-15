package com.efedaniel.ulesson.extensions

import android.app.Activity
import android.content.res.Configuration
import android.view.View

fun Activity.isLandScape(): Boolean {
    return resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}
