package com.wolt.restauranttime.presentation.timedisplay

import androidx.lifecycle.ViewModel
import com.wolt.restauranttime.domain.TimetableUseCases
import javax.inject.Inject

class TimeDisplayViewModel @Inject constructor(private val useCases: TimetableUseCases) :
    ViewModel() {

    fun getDisplayOpeningHours(): LinkedHashMap<String, String> {
        return useCases.getTimetableMap()
    }
}