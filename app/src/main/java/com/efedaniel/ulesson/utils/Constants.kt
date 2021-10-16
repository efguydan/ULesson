package com.efedaniel.ulesson.utils

object Constants {

    object Variables {
        const val SHARED_PREFS_NAME = "global_shared_prefs"
        const val BASE_URL = "https://mock-live-lessons.herokuapp.com/"
    }

    object DatabaseKeys {
        const val DATABASE_NAME = "ulesson_database.db"
        const val LESSON_TABLE_NAME = "lessons_table"
    }

    object Keys {
        const val UPCOMING = "upcoming"
        const val LIVE = "live"
        const val REPLAY = "replay"
    }

    object Data {
        val SUBJECTS = listOf(
            "All Subjects",
            "Mathematics",
            "English",
            "Chemistry",
            "Biology",
            "Physics"
        )
    }
}
