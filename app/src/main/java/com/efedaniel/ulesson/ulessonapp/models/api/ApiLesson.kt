package com.efedaniel.ulesson.ulessonapp.models.api

import com.efedaniel.ulesson.ulessonapp.models.local.LocalLesson
import com.google.gson.annotations.SerializedName

data class ApiLesson(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("media_url")
    val videoLink: String,
    @SerializedName("subject_id")
    val subjectID: Int,
    @SerializedName("chapter_id")
    val chapterID: Int
)

fun ApiLesson.toLocalLesson() = LocalLesson(
    id = id,
    name = name,
    icon = icon,
    videoLink = videoLink,
    subjectID = subjectID,
    chapterID = chapterID
)
