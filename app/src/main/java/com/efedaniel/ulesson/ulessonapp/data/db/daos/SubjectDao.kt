package com.efedaniel.ulesson.ulessonapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.efedaniel.ulesson.ulessonapp.models.local.LocalSubject
import com.efedaniel.ulesson.utils.Constants.DatabaseKeys

@Dao
interface SubjectDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(subject: LocalSubject)

    @Query("SELECT * FROM ${DatabaseKeys.SUBJECT_TABLE_NAME}")
    fun getAllSubjectsLive(): LiveData<List<LocalSubject>>

    @Query("SELECT * FROM ${DatabaseKeys.SUBJECT_TABLE_NAME}")
    suspend fun getAllSubjects(): List<LocalSubject>

    @Delete
    suspend fun delete(vararg subject: LocalSubject)

    @Query("DELETE FROM ${DatabaseKeys.SUBJECT_TABLE_NAME} WHERE id = :subjectID")
    suspend fun delete(subjectID: Int)
}
