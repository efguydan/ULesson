package com.efedaniel.ulesson.ulessonapp.data.repositories

import androidx.lifecycle.LiveData
import com.efedaniel.ulesson.ulessonapp.data.db.daos.ChapterDao
import com.efedaniel.ulesson.ulessonapp.data.db.daos.LessonDao
import com.efedaniel.ulesson.ulessonapp.data.db.daos.RecentlyWatchedDao
import com.efedaniel.ulesson.ulessonapp.data.db.daos.SubjectDao
import com.efedaniel.ulesson.ulessonapp.models.api.ApiSubject
import com.efedaniel.ulesson.ulessonapp.models.api.toLocalChapter
import com.efedaniel.ulesson.ulessonapp.models.api.toLocalLesson
import com.efedaniel.ulesson.ulessonapp.models.api.toLocalSubject
import com.efedaniel.ulesson.ulessonapp.models.general.Chapter
import com.efedaniel.ulesson.ulessonapp.models.local.LocalLesson
import com.efedaniel.ulesson.ulessonapp.models.local.LocalRecentlyWatched
import com.efedaniel.ulesson.ulessonapp.models.local.LocalSubject
import com.efedaniel.ulesson.ulessonapp.models.local.toChapter
import com.efedaniel.ulesson.ulessonapp.models.local.toLesson
import javax.inject.Inject

class LocalRepository @Inject constructor(
    private val subjectDao: SubjectDao,
    private val chapterDao: ChapterDao,
    private val lessonDao: LessonDao,
    private val recentlyWatchedDao: RecentlyWatchedDao
) {

    suspend fun insertSubjects(subjects: List<ApiSubject>) {
        val newSubjects = subjects.map { it.toLocalSubject() }
        val currentSubjectList = subjectDao.getAllSubjects()
        val subjectsToRemove = currentSubjectList.toMutableList().apply { removeAll(newSubjects) }

        removeSubjects(subjectsToRemove)
        addSubjects(subjects)
    }

    private suspend fun addSubjects(subjects: List<ApiSubject>) {
        for (subject in subjects) {
            for (chapter in subject.chapters) {
                for (lesson in chapter.lessons) {
                    lessonDao.insertLesson(lesson.toLocalLesson())
                }
                chapterDao.insert(chapter.toLocalChapter(subject.id))
            }
            subjectDao.insert(subject.toLocalSubject())
        }
    }

    private suspend fun removeSubjects(subjects: List<LocalSubject>) {
        for (subject in subjects) {
            chapterDao.removeSubjectChapters(subject.id)
            lessonDao.removeSubjectLessons(subject.id)
        }
        subjectDao.delete(*subjects.toTypedArray())
    }

    suspend fun getAllSubjects(): List<LocalSubject> {
        return subjectDao.getAllSubjects()
    }

    fun getAllSubjectsLive(): LiveData<List<LocalSubject>> {
        return subjectDao.getAllSubjectsLive()
    }

    suspend fun getSubjectChapters(subjectID: Int): List<Chapter> {
        return chapterDao.getSubjectChapters(subjectID).map { chapter ->
            chapter.toChapter(getChapterLessons(chapter.id).map { it.toLesson() })
        }
    }

    private suspend fun getChapterLessons(chapterID: Int): List<LocalLesson> {
        return lessonDao.getChapterLessons(chapterID)
    }

    fun getRecentlyWatchedLessons(limit: Int): LiveData<List<LocalRecentlyWatched>> {
        return recentlyWatchedDao.getAllRecentlyWatchedLive(limit)
    }

    suspend fun insertRecentlyWatched(lesson: LocalRecentlyWatched) {
        recentlyWatchedDao.insert(lesson)
        recentlyWatchedDao.keepItemsToFive()
    }
}
