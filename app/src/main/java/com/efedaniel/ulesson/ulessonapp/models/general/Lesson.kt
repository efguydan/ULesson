package com.efedaniel.ulesson.ulessonapp.models.general

import android.os.Parcelable
import com.efedaniel.ulesson.ulessonapp.models.local.LocalRecentlyWatched
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Lesson(
    val id: Int,
    val name: String,
    val icon: String,
    val videoLink: String
) : Parcelable

fun Lesson.toLocalRecentlyWatched(subjectName: String) = LocalRecentlyWatched(
    id = id,
    name = name,
    icon = icon,
    videoLink = videoLink,
    subjectName = subjectName
)
