package com.efedaniel.ulesson.ulessonapp.models.general

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Lesson(
    val id: String,
    val tutorFirstName: String,
    val tutorLastName: String,
    val subjectName: String,
    val imageLink: String,
    val status: String,
    val topicName: String,
    val startTime: String
) : Parcelable {

    fun getTutorFullName() = "$tutorFirstName $tutorLastName"

}
