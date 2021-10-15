package com.efedaniel.ulesson.ulessonapp.data.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.efedaniel.ulesson.ulessonapp.models.local.LocalRecentlyWatched
import com.efedaniel.ulesson.utils.Constants.DatabaseKeys.RECENTLY_WATCHED_TABLE_NAME

@Dao
interface RecentlyWatchedDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lesson: LocalRecentlyWatched)

    @Query("DELETE FROM $RECENTLY_WATCHED_TABLE_NAME where id NOT IN (SELECT id from $RECENTLY_WATCHED_TABLE_NAME ORDER BY created_at DESC LIMIT 5)")
    suspend fun keepItemsToFive()

    @Query("SELECT * FROM $RECENTLY_WATCHED_TABLE_NAME ORDER BY created_at DESC LIMIT :limit")
    fun getAllRecentlyWatchedLive(limit: Int): LiveData<List<LocalRecentlyWatched>>
}
