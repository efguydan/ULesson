package com.efedaniel.ulesson.di

import android.app.Application
import com.efedaniel.ulesson.ulessonapp.screens.live.LiveFragment
import com.efedaniel.ulesson.ulessonapp.screens.me.MeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [APIServiceModule::class, ViewModelModule::class])
interface AppComponent {

    fun inject(target: LiveFragment)
    fun inject(target: MeFragment)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent

        @BindsInstance
        fun application(app: Application): Builder
    }
}
