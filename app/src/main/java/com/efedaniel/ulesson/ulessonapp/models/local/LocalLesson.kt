package com.efedaniel.ulesson.ulessonapp.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import com.efedaniel.ulesson.utils.Constants

@Entity(tableName = Constants.DatabaseKeys.LESSON_TABLE_NAME)
data class LocalLesson(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "icon")
    val icon: String,
    @ColumnInfo(name = "video_link")
    val videoLink: String,
    @ColumnInfo(name = "subject_id")
    val subjectID: Int,
    @ColumnInfo(name = "chapter_id")
    val chapterID: Int
)

fun LocalLesson.toLesson() = Lesson(
    id = id,
    name = name,
    icon = icon,
    videoLink = videoLink
)
