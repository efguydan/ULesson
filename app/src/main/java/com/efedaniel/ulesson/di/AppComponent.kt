package com.efedaniel.ulesson.di

import android.app.Application
import com.efedaniel.ulesson.ulessonapp.screens.dashboard.DashboardFragment
import com.efedaniel.ulesson.ulessonapp.screens.lesson.LessonFragment
import com.efedaniel.ulesson.ulessonapp.screens.subject.SubjectFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [APIServiceModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(target: DashboardFragment)
    fun inject(target: SubjectFragment)
    fun inject(target: LessonFragment)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(app: Application): Builder
    }
}
