package com.efedaniel.ulesson.ulessonapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.efedaniel.ulesson.ulessonapp.data.db.daos.LessonDao
import com.efedaniel.ulesson.ulessonapp.models.local.LocalLesson

@Database(
    entities = [
        LocalLesson::class
    ],
    version = 1, exportSchema = false
)
abstract class ULessonDatabase : RoomDatabase() {

    abstract fun getLessonDao(): LessonDao

}
