package com.efedaniel.ulesson.ulessonapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.efedaniel.ulesson.ulessonapp.models.local.LocalLesson
import com.efedaniel.ulesson.utils.Constants.DatabaseKeys.LESSON_TABLE_NAME

@Dao
interface LessonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lesson: List<LocalLesson>)

    @Query("SELECT * FROM $LESSON_TABLE_NAME WHERE id = :id")
    suspend fun getLesson(id: String): LocalLesson?

    @Query("SELECT * FROM $LESSON_TABLE_NAME WHERE subjectName = :subjectName")
    fun observeLessonsBySubject(subjectName: String): LiveData<List<LocalLesson>>

    @Query("SELECT * FROM $LESSON_TABLE_NAME")
    fun observeAllMyLessons(): LiveData<List<LocalLesson>>

    @Query("DELETE FROM $LESSON_TABLE_NAME")
    suspend fun clearTable()
}
