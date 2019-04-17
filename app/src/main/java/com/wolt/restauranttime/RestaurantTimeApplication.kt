package com.wolt.restauranttime

import android.app.Application
import com.wolt.restauranttime.di.DaggerWoltApplicationComponent
import com.wolt.restauranttime.di.WoltApplicationComponent
import timber.log.Timber

class RestaurantTimeApplication : Application() {

    lateinit var component: WoltApplicationComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        component = DaggerWoltApplicationComponent.builder().build()
    }
}