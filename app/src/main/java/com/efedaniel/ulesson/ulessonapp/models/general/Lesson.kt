package com.efedaniel.ulesson.ulessonapp.models.general

import android.os.Parcelable
import com.efedaniel.ulesson.extensions.EMPTY
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

    companion object

    fun getTutorFullName() = "$tutorFirstName $tutorLastName"
}

val Lesson.Companion.EMPTY: Lesson
    get() = Lesson(
        id = String.EMPTY,
        tutorFirstName = String.EMPTY,
        tutorLastName = String.EMPTY,
        subjectName = String.EMPTY,
        imageLink = String.EMPTY,
        status = String.EMPTY,
        topicName = String.EMPTY,
        startTime = String.EMPTY
    )
