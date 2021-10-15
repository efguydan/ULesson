package com.efedaniel.ulesson.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.efedaniel.ulesson.ulessonapp.screens.dashboard.DashboardViewModel
import com.efedaniel.ulesson.ulessonapp.screens.lesson.LessonViewModel
import com.efedaniel.ulesson.ulessonapp.screens.subject.SubjectViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SubjectViewModel::class)
    abstract fun bindSubjectViewModel(viewModel: SubjectViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LessonViewModel::class)
    abstract fun bindLessonViewModel(viewModel: LessonViewModel): ViewModel
}
