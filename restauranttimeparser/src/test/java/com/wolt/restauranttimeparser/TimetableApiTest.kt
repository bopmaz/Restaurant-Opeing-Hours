package com.wolt.restauranttimeparser

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class TimetableApiTest {

    private val gson: Gson =
        GsonBuilder().registerTypeAdapter(Timetable::class.java, TimetableDeserializer()).create()

    @Test
    fun test_get_time_table_map_return_correctly() {
        val timeTable = gson.fromJson<Timetable>(TestCaseStrings.normalHours, Timetable::class.java)

        val timeTableMap = TimetableApi.getTimetableMap(timeTable)

        assertNotNull(timeTableMap)
        assertEquals(7, timeTableMap.size)
    }

    @Test
    fun test_print_time_table_map_to_string_return_correctly() {
        val timeTable = gson.fromJson<Timetable>(TestCaseStrings.normalHours, Timetable::class.java)

        val timeTableString = TimetableApi.printResultString(TestCaseStrings.normalHours)
        val expectedString = "monday:Closed\n" +
                "tuesday:10:00 AM - 06:00 PM\n" +
                "wednesday:Closed\n" +
                "thursday:10:00 AM - 06:00 PM\n" +
                "friday:10:00 AM - 01:00 AM\n" +
                "saturday:10:00 AM - 01:00 AM\n" +
                "sunday:12:00 PM - 09:00 PM\n"

        assertEquals(expectedString, timeTableString)
    }

}