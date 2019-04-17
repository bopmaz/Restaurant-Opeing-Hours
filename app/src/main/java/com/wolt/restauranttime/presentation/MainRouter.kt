package com.wolt.restauranttime.presentation

import android.content.Intent
import com.wolt.restauranttime.presentation.timedisplay.TimeDisplayActivity
import com.wolt.restauranttime.presentation.timeselector.TimeSelectorActivity
import java.lang.ref.WeakReference

class MainRouter(private val activityReference: WeakReference<TimeSelectorActivity>) {

    fun navigateToDetail() {
        activityReference.get()
            ?.startActivity(Intent(activityReference.get(), TimeDisplayActivity::class.java))
    }

}
