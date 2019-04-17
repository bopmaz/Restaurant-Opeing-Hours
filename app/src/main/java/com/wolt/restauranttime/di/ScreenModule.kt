package com.wolt.restauranttime.di

import com.wolt.restauranttime.presentation.MainRouter
import com.wolt.restauranttime.presentation.timeselector.TimeSelectorActivity
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

@Module
class ScreenModule(private val activity: TimeSelectorActivity) {

    @Provides
    fun provideMainRouter(): MainRouter {
        return MainRouter(WeakReference(activity))
    }
}