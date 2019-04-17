package com.wolt.restauranttime.data

import com.wolt.restauranttimeparser.Timetable
import io.reactivex.Single
import javax.inject.Inject


interface TimetableRepositoryInterface {
    var storedTimetable: Timetable

    fun fetchTimeTable(url: String): Single<Timetable>
}

class TimetableRepository @Inject constructor() : TimetableRepositoryInterface {

    override var storedTimetable: Timetable = Timetable()

    @Inject
    lateinit var woltService: WoltService

    override fun fetchTimeTable(url: String): Single<Timetable> {
        return woltService.getTimeTable(url)
    }
}