package com.wolt.restauranttime

import androidx.appcompat.app.AppCompatActivity
import com.wolt.restauranttime.di.ScreenComponent
import com.wolt.restauranttime.di.ScreenModule
import com.wolt.restauranttime.di.WoltApplicationComponent

open class BaseActivity : AppCompatActivity() {

    val screenComponent: ScreenComponent by lazy {
        getApplicationComponent().screenComponent(ScreenModule(this))
    }

    private fun getApplicationComponent(): WoltApplicationComponent {
        return (application as RestaurantTimeApplication).component
    }

}