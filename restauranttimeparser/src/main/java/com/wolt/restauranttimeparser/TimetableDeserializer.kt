package com.wolt.restauranttimeparser

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class TimetableDeserializer : JsonDeserializer<Timetable> {

    val gson = GsonBuilder().create()
    val type = object : TypeToken<LinkedHashMap<String, List<RestaurantTime>>>() {}.type

    enum class TimerReader {
        READTIMETYPE,
        WRITEPREVIOUS
    }

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Timetable {
        val result = Timetable()

        var previousKey = ""
        var lastWeekDayCloseTime = -1

        val mapInput =
            gson.fromJson<LinkedHashMap<String, List<RestaurantTime>>>(json.asJsonObject, type)

        mapInput.toList().forEachIndexed() { index, it ->
            val date: String = it.first

            if (index == 0 && getLastWeekDayOpen(it.second) != -1) {
                lastWeekDayCloseTime = getLastWeekDayOpen(it.second)
            }

            val (timeOpenList, lastDayClosingTime, isDayClosed)
                    = getOpeningHourList(it.second)

            if (!isDayClosed && index != 0) {
                result.time.getValue(previousKey).last().close = lastDayClosingTime
            }
            previousKey = it.first

            result.time[date] = timeOpenList

            if (lastWeekDayCloseTime != -1 && index == mapInput.toList().size - 1) {
                result.time[date]!!.last().close = lastWeekDayCloseTime
            }
        }

        return result
    }

    private fun getLastWeekDayOpen(timeList: List<RestaurantTime>): Int {
        if (timeList.isEmpty()) {
            return -1
        }

        if (timeList[0].type.equals("close")) {
            return timeList[0].value
        } else {
            return -1
        }
    }


    private fun getOpeningHourList(
        restaurantTimes: List<RestaurantTime>
    ): Triple<MutableList<OpeningHours>, Int, Boolean> {

        if (restaurantTimes.isEmpty()) {
            return Triple(mutableListOf(), -1, true)
        }

        val openingHoursList = mutableListOf<OpeningHours>()

        var lastClosingTime = -1
        var currentPosition = 0
        var isDateClosed = true

        restaurantTimes.forEach {
            when (it.type) {
                "open" -> {
                    val currentOpenHour = OpeningHours()
                    currentOpenHour.open = it.value
                    openingHoursList.add(currentPosition, currentOpenHour)
                }

                "close" -> {
                    if (openingHoursList.size == 0) {
                        lastClosingTime = it.value
                        isDateClosed = false
                    } else {
                        openingHoursList[currentPosition].close = it.value
                        currentPosition++
                    }
                }
            }
        }

        return Triple(openingHoursList, lastClosingTime, isDateClosed)
    }
}

data class RestaurantTime(val type: String, val value: Int)

data class OpeningHours(var open: Int = 0, var close: Int = 0)

data class Timetable(var time: LinkedHashMap<String, List<OpeningHours>> = linkedMapOf())