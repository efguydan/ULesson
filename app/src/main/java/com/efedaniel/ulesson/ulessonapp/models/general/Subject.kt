package com.efedaniel.ulesson.ulessonapp.models.general

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Subject(
    val id: Int,
    val name: String,
    val icon: String
) : Parcelable
