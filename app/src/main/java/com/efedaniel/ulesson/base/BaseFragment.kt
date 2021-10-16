package com.efedaniel.ulesson.base

import androidx.fragment.app.Fragment
import com.efedaniel.ulesson.App
import com.efedaniel.ulesson.MainActivity
import com.efedaniel.ulesson.di.AppComponent

abstract class BaseFragment : Fragment() {

    protected val mainActivity: MainActivity
        get() {
            return activity as? MainActivity ?: throw IllegalStateException("Not attached!")
        }

    protected val daggerAppComponent: AppComponent
        get() = (mainActivity.applicationContext as App).component
}
