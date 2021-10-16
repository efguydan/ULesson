package com.efedaniel.ulesson.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.efedaniel.ulesson.ulessonapp.data.db.ULessonDatabase
import com.efedaniel.ulesson.ulessonapp.data.db.daos.LessonDao
import com.efedaniel.ulesson.utils.Constants
import com.efedaniel.ulesson.utils.PrefsUtils
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Provides
    @Singleton
    fun providesPrefsUtils(prefs: SharedPreferences, gson: Gson): PrefsUtils = PrefsUtils(prefs, gson)

    @Provides
    @Singleton
    fun providesGlobalSharedPreference(app: Application): SharedPreferences =
        app.getSharedPreferences(Constants.Variables.SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    @Provides
    @Singleton
    fun providesULessonDatabase(app: Application): ULessonDatabase = Room.databaseBuilder(
        app,
        ULessonDatabase::class.java,
        Constants.DatabaseKeys.DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun providesLessonDao(db: ULessonDatabase): LessonDao = db.getLessonDao()

}
