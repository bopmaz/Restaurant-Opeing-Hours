package com.wolt.restauranttime.di

import com.wolt.restauranttime.data.WoltService
import dagger.Component

@WoltApplicationScope
@Component(modules = [WoltServiceModule::class])
interface WoltApplicationComponent {

    fun woltService(): WoltService

    fun screenComponent(screenModule: ScreenModule): ScreenComponent

}
