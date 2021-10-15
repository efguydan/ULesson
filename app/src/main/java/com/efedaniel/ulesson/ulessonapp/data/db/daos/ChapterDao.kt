package com.efedaniel.ulesson.ulessonapp.data.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.efedaniel.ulesson.ulessonapp.models.local.LocalChapter
import com.efedaniel.ulesson.utils.Constants.DatabaseKeys

@Dao
interface ChapterDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(chapter: LocalChapter)

    @Query("SELECT * FROM ${DatabaseKeys.CHAPTER_TABLE_NAME} where id = :chapterID")
    suspend fun getChapter(chapterID: Int): LocalChapter?

    @Delete
    suspend fun delete(lesson: LocalChapter)

    @Query("DELETE FROM ${DatabaseKeys.CHAPTER_TABLE_NAME} WHERE subject_id = :subjectID")
    suspend fun removeSubjectChapters(subjectID: Int)

    @Query("SELECT * FROM ${DatabaseKeys.CHAPTER_TABLE_NAME} where subject_id = :subjectID")
    suspend fun getSubjectChapters(subjectID: Int): List<LocalChapter>
}
