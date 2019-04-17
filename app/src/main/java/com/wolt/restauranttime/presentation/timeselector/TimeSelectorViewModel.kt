package com.wolt.restauranttime.presentation.timeselector

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.google.gson.JsonParseException
import com.wolt.restauranttime.domain.TimetableUseCases
import com.wolt.restauranttime.presentation.MainRouter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class TimeSelectorViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var useCases: TimetableUseCases
    @Inject
    lateinit var router: MainRouter

    private val disposables = CompositeDisposable()

    val plainTextJson = ObservableField<String>("")
    val linkText = ObservableField<String>("")
    val isLoading = ObservableBoolean()
    val errorObservable = BehaviorSubject.create<String>()


    fun fetchExampleApi(exampleNumber: Int) {

    }

    fun fetchLinkApi() {
        if (linkText.get().toString().isEmpty()) {
            return
        }

        fetchTimeTable(linkText.get().toString())
    }

    private fun fetchTimeTable(apiLink: String) {
        disposables.add(useCases.fetchTimeTable(apiLink)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                onTimeTableFetched(it)
            }
        )
    }

    private fun onTimeTableFetched(timetableFetchedResult: TimetableUseCases.TimetableFetchResult) {
        isLoading.set(timetableFetchedResult == TimetableUseCases.TimetableFetchResult.Loading)

        when (timetableFetchedResult) {
            is TimetableUseCases.TimetableFetchResult.Success -> {
                router.navigateToDetail()
            }

            is TimetableUseCases.TimetableFetchResult.Failure -> {
                errorObservable.onNext(timetableFetchedResult.error.toString())
            }
        }
    }

    fun getOpeningHours() {
        if (plainTextJson.get().toString().isEmpty()) {
            return
        }

        try {
            useCases.setStoredTimetable(plainTextJson.get().toString())
            router.navigateToDetail()
        } catch (e: JsonParseException) {
            errorObservable.onNext(e.toString())
        }
    }

    fun unbound() {
        disposables.clear()
    }

}