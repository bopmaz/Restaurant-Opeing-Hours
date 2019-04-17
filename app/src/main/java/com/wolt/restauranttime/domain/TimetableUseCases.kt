package com.wolt.restauranttime.domain

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.wolt.restauranttime.data.TimetableRepository
import com.wolt.restauranttimeparser.Timetable
import io.reactivex.Observable
import javax.inject.Inject

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

    fun getStoredTimetable(): Timetable {
        return repository.storedTimetable
    }
}