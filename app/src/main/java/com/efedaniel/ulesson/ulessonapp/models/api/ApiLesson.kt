package com.efedaniel.ulesson.ulessonapp.models.api

import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import com.efedaniel.ulesson.ulessonapp.models.local.LocalLesson
import com.google.gson.annotations.SerializedName

data class ApiLesson(
    @SerializedName("id")
    val id: String,
    @SerializedName("tutor")
    val tutor: ApiTutor = ApiTutor(),
    @SerializedName("subject")
    val subject: ApiSubject,
    @SerializedName("image_url")
    val imageLink: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("topic")
    val topicName: String,
    @SerializedName("start_at")
    val startTime: String
)

fun ApiLesson.toLessonModel(): Lesson = Lesson(
    id = id,
    tutorFirstName = tutor.firstName.orEmpty(),
    tutorLastName = tutor.lastName.orEmpty(),
    subjectName = subject.name,
    imageLink = imageLink,
    status = status,
    topicName = topicName,
    startTime = startTime
)

fun ApiLesson.toLocalLessonModel(): LocalLesson = LocalLesson(
    id = id,
    tutorFirstName = tutor.firstName.orEmpty(),
    tutorLastName = tutor.lastName.orEmpty(),
    subjectName = subject.name,
    imageLink = imageLink,
    status = status,
    topicName = topicName,
    startTime = startTime
)
