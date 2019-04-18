package com.wolt.restauranttimeparser

import com.google.gson.*
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class TimetableDeserializer : JsonDeserializer<Timetable> {

    val gson = GsonBuilder().create()
    val type = object : TypeToken<LinkedHashMap<String, List<RestaurantTime>>>() {}.type

    @Throws(JsonParseException::class)
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Timetable {
        val result = Timetable()

        var yesterdayName = ""
        var lastWeekDayCloseTime = -1

        val mapInput =
            gson.fromJson<LinkedHashMap<String, List<RestaurantTime>>>(json.asJsonObject, type)

        mapInput.toList().forEachIndexed() { index, it ->
            val date: String = it.first

            if (index == 0 && getLastWeekDayClosingTime(it.second) != -1) {
                lastWeekDayCloseTime = getLastWeekDayClosingTime(it.second)
            }

            val (timeOpenList, lastDayClosingTime)
                    = getOpeningHourList(it.second)

            if (lastDayClosingTime != -1 && index != 0) {
                result.time.getValue(yesterdayName).last().close = lastDayClosingTime
            }

            yesterdayName = it.first
            result.time[date] = timeOpenList

            if (lastWeekDayCloseTime != -1 && index == mapInput.toList().size - 1) {
                result.time[date]!!.last().close = lastWeekDayCloseTime
            }
        }

        return result
    }

    /**
     *
     * Use for case last day of the week open overnight
     * @param timeList list of data class for original schema {"type":"open", time:"36000"}
     * @return Last week day closing time
     */
    private fun getLastWeekDayClosingTime(timeList: List<RestaurantTime>): Int {
        if (timeList.isEmpty()) {
            return -1
        }

        if (timeList[0].type.equals("close")) {
            return timeList[0].value
        } else {
            return -1
        }
    }

    /**
     *
     *
     * @param restaurantTimes list of data class for original schema {"type":"open", time: 36000}
     * @return A triple which include:
     * - Opening hour list following the schema {"open": 36000, "close": 72000}
     * - Int Which is used for the case the restaurant open overnight, if not return -1
     *
     */
    private fun getOpeningHourList(
        restaurantTimes: List<RestaurantTime>
    ): Pair<MutableList<OpeningHours>, Int> {

        if (restaurantTimes.isEmpty()) {
            return Pair(mutableListOf(), -1)
        }

        val openingHoursList = mutableListOf<OpeningHours>()

        var lastClosingTime = -1
        var currentPosition = 0

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
                    } else {
                        openingHoursList[currentPosition].close = it.value
                        currentPosition++
                    }
                }
            }
        }

        return Pair(openingHoursList, lastClosingTime)
    }
}

data class RestaurantTime(val type: String, val value: Int)

data class OpeningHours(var open: Int = 0, var close: Int = 0)

data class Timetable(var time: LinkedHashMap<String, List<OpeningHours>> = linkedMapOf())