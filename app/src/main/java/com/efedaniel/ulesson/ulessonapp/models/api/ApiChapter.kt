package com.efedaniel.ulesson.ulessonapp.models.api

import com.efedaniel.ulesson.ulessonapp.models.local.LocalChapter
import com.google.gson.annotations.SerializedName

data class ApiChapter(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("lessons")
    val lessons: List<ApiLesson>
)

fun ApiChapter.toLocalChapter(subjectID: Int) = LocalChapter(
    id = id,
    name = name,
    subjectID = subjectID
)
