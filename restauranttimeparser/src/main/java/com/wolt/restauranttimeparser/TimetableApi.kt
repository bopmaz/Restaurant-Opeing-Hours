package com.wolt.restauranttimeparser

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.LinkedHashMap

class TimetableApi {

    companion object {

        fun printMapToString(timetableResult: LinkedHashMap<String, String>): String {
            val stringBuilder = StringBuilder()

            timetableResult.toList().forEach {
                stringBuilder.append(it.first)
                    .append(":")
                    .append(it.second)
                    .append("\n")
            }

            return stringBuilder.toString()
        }

        fun getTimetableMap(timeTable: Timetable): LinkedHashMap<String, String> {
            val result = LinkedHashMap<String, String>()

            timeTable.time.toList().forEach {
                result[it.first] = getDayHoursString(it.second)
            }

            return result
        }

        private fun getDayHoursString(openingList: List<OpeningHours>): String {

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

        @SuppressLint("SimpleDateFormat")
        private fun getTimeString(time: Int): String {
            val simpleDateFormat = SimpleDateFormat("hh:mm a")
            simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
            return simpleDateFormat.format(Date(time * 1000L))
        }
    }
}