package com.efedaniel.ulesson.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.efedaniel.ulesson.ulessonapp.screens.live.LiveViewModel
import com.efedaniel.ulesson.ulessonapp.screens.me.MeViewModel
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
    @ViewModelKey(LiveViewModel::class)
    abstract fun bindLiveViewModel(viewModel: LiveViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MeViewModel::class)
    abstract fun bindMeViewModel(viewModel: MeViewModel): ViewModel

}
