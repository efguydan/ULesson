package com.efedaniel.ulesson.ulessonapp.models.general

import com.efedaniel.ulesson.ulessonapp.models.local.LocalRecentlyWatched

data class RecentlyWatched(
    val id: Int,
    val name: String,
    val icon: String,
    val videoLink: String,
    val subjectName: String
)

fun RecentlyWatched.toLesson() = Lesson(
    id = id,
    name = name,
    icon = icon,
    videoLink = videoLink
)

fun RecentlyWatched.toLocalRecentlyWatched() = LocalRecentlyWatched(
    id = id,
    name = name,
    icon = icon,
    videoLink = videoLink,
    subjectName = subjectName
)
