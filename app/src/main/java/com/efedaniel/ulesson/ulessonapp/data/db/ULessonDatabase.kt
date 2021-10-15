package com.efedaniel.ulesson.ulessonapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.efedaniel.ulesson.ulessonapp.data.db.daos.ChapterDao
import com.efedaniel.ulesson.ulessonapp.data.db.daos.LessonDao
import com.efedaniel.ulesson.ulessonapp.data.db.daos.RecentlyWatchedDao
import com.efedaniel.ulesson.ulessonapp.data.db.daos.SubjectDao
import com.efedaniel.ulesson.ulessonapp.models.local.LocalChapter
import com.efedaniel.ulesson.ulessonapp.models.local.LocalLesson
import com.efedaniel.ulesson.ulessonapp.models.local.LocalRecentlyWatched
import com.efedaniel.ulesson.ulessonapp.models.local.LocalSubject

@Database(
    entities = [
        LocalSubject::class,
        LocalChapter::class,
        LocalLesson::class,
        LocalRecentlyWatched::class
    ],
    version = 1, exportSchema = false
)
abstract class ULessonDatabase : RoomDatabase() {

    abstract fun getSubjectDao(): SubjectDao
    abstract fun getChapterDao(): ChapterDao
    abstract fun getLessonDao(): LessonDao
    abstract fun getRecentlyWatchedDao(): RecentlyWatchedDao
}
