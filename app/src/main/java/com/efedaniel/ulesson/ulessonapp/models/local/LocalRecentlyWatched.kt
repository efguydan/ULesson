package com.efedaniel.ulesson.ulessonapp.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.efedaniel.ulesson.ulessonapp.models.general.RecentlyWatched
import com.efedaniel.ulesson.utils.Constants

@Entity(tableName = Constants.DatabaseKeys.RECENTLY_WATCHED_TABLE_NAME)
data class LocalRecentlyWatched(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "icon")
    val icon: String,
    @ColumnInfo(name = "video_link")
    val videoLink: String,
    @ColumnInfo(name = "subject_name")
    val subjectName: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)

fun LocalRecentlyWatched.toRecentlyWatched() = RecentlyWatched(
    id = id,
    name = name,
    icon = icon,
    videoLink = videoLink,
    subjectName = subjectName
)
