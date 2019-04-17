package com.wolt.restauranttime.domain

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.wolt.restauranttime.data.TimetableRepository
import com.wolt.restauranttime.di.WoltApplicationScope
import com.wolt.restauranttimeparser.OpeningHours
import com.wolt.restauranttimeparser.Timetable
import io.reactivex.Observable
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.LinkedHashMap

@WoltApplicationScope
class TimetableUseCases @Inject constructor() {

    sealed class TimetableFetchResult {
        object Loading : TimetableFetchResult()
        data class Success(val timetable: Timetable) : TimetableFetchResult()
        data class Failure(val error: Throwable) : TimetableFetchResult()
    }

    @Inject
    lateinit var repository: TimetableRepository
    @Inject
    lateinit var gson: Gson

    fun fetchTimeTable(url: String): Observable<TimetableFetchResult> {
        return repository.fetchTimeTable(url)
            .toObservable()
            .map {
                repository.storedTimetable = it
                TimetableFetchResult.Success(it) as TimetableFetchResult
            }
            .onErrorReturn {
                TimetableFetchResult.Failure(it)
            }
            .startWith(TimetableFetchResult.Loading)
    }

    fun setStoredTimetable(storedTimetable: String) {
        try {
            repository.storedTimetable =
                gson.fromJson<Timetable>(storedTimetable, Timetable::class.java)
        } catch (error: JsonParseException) {
            throw error
        }
    }

    fun getTimetableMap(): LinkedHashMap<String, String> {
        val result = LinkedHashMap<String, String>()

        repository.storedTimetable.time.toList().forEach {
            result[it.first] = getDayHoursString(it.second)
        }

        return result
    }

    private fun getDayHoursString(openingList: List<OpeningHours>): String {

        if (openingList.isEmpty()) {
            return "Closed"
        }

        val stringBuilder = StringBuilder()

        openingList.forEach {
            stringBuilder.append(getTimeString(it.open))
                .append(" - ")
                .append(getTimeString(it.close))
        }

        return stringBuilder.toString()
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTimeString(time: Int): String {
        val simpleDateFormat = SimpleDateFormat("hh:mm a")
        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        return simpleDateFormat.format(Date(time * 1000L))

    }
}