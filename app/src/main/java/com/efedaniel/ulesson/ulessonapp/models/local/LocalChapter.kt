package com.efedaniel.ulesson.ulessonapp.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.efedaniel.ulesson.ulessonapp.models.general.Chapter
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import com.efedaniel.ulesson.utils.Constants

@Entity(tableName = Constants.DatabaseKeys.CHAPTER_TABLE_NAME)
data class LocalChapter(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "subject_id")
    val subjectID: Int
)

fun LocalChapter.toChapter(lessons: List<Lesson>) = Chapter(
    id = id,
    name = name,
    lessons = lessons
)
