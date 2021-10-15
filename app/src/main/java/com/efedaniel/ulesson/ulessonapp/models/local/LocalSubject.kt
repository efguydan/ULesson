package com.efedaniel.ulesson.ulessonapp.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.efedaniel.ulesson.ulessonapp.models.general.Subject
import com.efedaniel.ulesson.utils.Constants

@Entity(tableName = Constants.DatabaseKeys.SUBJECT_TABLE_NAME)
data class LocalSubject(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "icon")
    val icon: String
)

fun LocalSubject.toSubject() = Subject(
    id = id,
    name = name,
    icon = icon
)
