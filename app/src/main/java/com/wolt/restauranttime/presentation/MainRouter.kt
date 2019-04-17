package com.wolt.restauranttime.presentation

import android.content.Intent
import com.wolt.restauranttime.BaseActivity
import com.wolt.restauranttime.presentation.timedisplay.TimeDisplayActivity
import java.lang.ref.WeakReference

class MainRouter(private val activityReference: WeakReference<BaseActivity>) {

    fun navigateToDetail() {
        activityReference.get()
            ?.startActivity(Intent(activityReference.get(), TimeDisplayActivity::class.java))
    }

}
