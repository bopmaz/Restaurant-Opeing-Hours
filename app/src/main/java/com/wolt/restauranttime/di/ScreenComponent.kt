package com.wolt.restauranttime.di

import com.wolt.restauranttime.presentation.timeselector.TimeSelectorActivity
import dagger.Subcomponent

@WoltScreenScope
@Subcomponent(modules = [ScreenModule::class])
interface ScreenComponent {

    fun inject(activity: TimeSelectorActivity)
}