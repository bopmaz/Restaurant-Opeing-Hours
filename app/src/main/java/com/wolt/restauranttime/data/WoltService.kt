package com.wolt.restauranttime.data

import com.wolt.restauranttimeparser.Timetable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface WoltService {

    @GET
    fun getTimeTable(@Url url: String): Single<Timetable>
}
