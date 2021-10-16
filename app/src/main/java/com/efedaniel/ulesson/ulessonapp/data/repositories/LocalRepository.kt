package com.efedaniel.ulesson.ulessonapp.data.repositories

import com.efedaniel.ulesson.ulessonapp.data.db.daos.LessonDao
import com.efedaniel.ulesson.ulessonapp.models.local.LocalLesson
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val lessonDao: LessonDao
) {

    suspend fun cacheMyLessons(lessons: List<LocalLesson>) {
        lessonDao.clearTable()
        lessonDao.insert(lessons)
    }

}
