package com.efedaniel.ulesson.ulessonapp.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.efedaniel.ulesson.ulessonapp.models.general.Lesson
import com.efedaniel.ulesson.utils.Constants

@Entity(tableName = Constants.DatabaseKeys.LESSON_TABLE_NAME)
data class LocalLesson(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "tutorFirstName")
    val tutorFirstName: String,
    @ColumnInfo(name = "tutorLastName")
    val tutorLastName: String,
    @ColumnInfo(name = "subjectName")
    val subjectName: String,
    @ColumnInfo(name = "image_url")
    val imageLink: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "topic_name")
    val topicName: String,
    @ColumnInfo(name = "start_time")
    val startTime: String
)

fun LocalLesson.toLessonModel(): Lesson = Lesson(
    id = id,
    tutorFirstName = tutorFirstName,
    tutorLastName = tutorLastName,
    subjectName = subjectName,
    imageLink = imageLink,
    status = status,
    topicName = topicName,
    startTime = startTime
)
