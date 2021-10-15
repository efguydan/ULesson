package com.efedaniel.ulesson.ulessonapp.models.general

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Chapter(
    val id: Int,
    val name: String,
    val lessons: List<Lesson>
) : Parcelable
