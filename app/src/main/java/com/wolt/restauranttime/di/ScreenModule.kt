package com.wolt.restauranttime.di

import com.wolt.restauranttime.BaseActivity
import com.wolt.restauranttime.presentation.MainRouter
import dagger.Module
import dagger.Provides
import java.lang.ref.WeakReference

@Module
class ScreenModule(private val activity: BaseActivity) {

    @Provides
    fun provideMainRouter(): MainRouter {
        return MainRouter(WeakReference(activity))
    }
}