package com.wolt.restauranttimeparser

import android.annotation.SuppressLint
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.LinkedHashMap

class TimetableApi {

    companion object {

        /**
         * Print timetable as String given raw string json input
         */
        fun printResultString(input: String): String {
            val gson: Gson =
                GsonBuilder().registerTypeAdapter(Timetable::class.java, TimetableDeserializer())
                    .create()

            val timetableResult =
                getTimetableMap(gson.fromJson<Timetable>(input, Timetable::class.java))

            val stringBuilder = StringBuilder()

            timetableResult.toList().forEach {
                stringBuilder.append(it.first)
                    .append(":")
                    .append(it.second)
                    .append("\n")
            }

            return stringBuilder.toString()
        }

        /**
         * Get timetable as map
         *
         * @param timeTable Timetable object, obtain through gson parser with deserializer adapter
         * @return Return a Kotlin LinkedHashMap with the format <"day of week": "time open">
         */
        fun getTimetableMap(timeTable: Timetable): LinkedHashMap<String, String> {
            val result = LinkedHashMap<String, String>()

            timeTable.time.toList().forEach {
                result[it.first] = getOpeningHoursString(it.second)
            }

            return result
        }

        /**
         * Get opening hour of the day
         *
         * @param openingList List of OpeningHours object with schema {"open":36000, "close":72000}
         * @return  Raw string of
         */
        private fun getOpeningHoursString(openingList: List<OpeningHours>): String {

            if (openingList.isEmpty()) {
                return "Closed"
            }

            val stringBuilder = StringBuilder()

            openingList.forEachIndexed() { index, it ->

                if (index != 0) {
                    stringBuilder.append("\n")
                }

                stringBuilder.append(getTimeString(it.open))
                    .append(" - ")
                    .append(getTimeString(it.close))
            }

            return stringBuilder.toString()
        }

        /**
         * Time parser
         *
         * @param time Unix time
         * @return Human-readable time with AM and PM format
         */
        @SuppressLint("SimpleDateFormat")
        private fun getTimeString(time: Int): String {
            val simpleDateFormat = SimpleDateFormat("hh:mm a")
            simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
            return simpleDateFormat.format(Date(time * 1000L))
        }
    }
}