package com.wolt.restauranttimeparser

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.junit.Assert.*
import org.junit.Test

class RestaurantAdapterTest {

    private val gson: Gson =
        GsonBuilder().registerTypeAdapter(Timetable::class.java, TimetableDeserializer()).create()

    @Test
    fun test_normal_time_should_parse_correctly() {
        val timeTable = gson.fromJson<Timetable>(TestCaseStrings.normalHours, Timetable::class.java)

        assertNotNull(timeTable)
        assertTrue(timeTable.time["monday"]!!.isEmpty())
        assertEquals(36000, timeTable.time["tuesday"]!![0].open)
        assertEquals(64800, timeTable.time["tuesday"]!![0].close)
        assertEquals(36000, timeTable.time["friday"]!![0].open)
        assertEquals(3600, timeTable.time["friday"]!![0].close)
    }

    @Test
    fun test_two_opening_time_should_parse_correctly() {
        val timeTable =
            gson.fromJson<Timetable>(TestCaseStrings.twoOpeningHours, Timetable::class.java)

        assertNotNull(timeTable)
        assertTrue(timeTable.time["thursday"]!!.size == 2)
    }

    @Test
    fun test_two_opening_time_overnight_should_parse_correctly() {
        val timeTable =
            gson.fromJson<Timetable>(
                TestCaseStrings.twoOpeningHoursOvernight,
                Timetable::class.java
            )

        assertNotNull(timeTable)
        assertTrue(timeTable.time["thursday"]!!.size == 2)
    }

    @Test
    fun test_last_day_opening_time_overnight_should_parse_correctly() {
        val timeTable =
            gson.fromJson<Timetable>(
                TestCaseStrings.lastDayOvernight,
                Timetable::class.java
            )

        assertNotNull(timeTable)
        assertTrue(timeTable.time["monday"]!!.isEmpty())
        assertTrue(timeTable.time["sunday"]!![0].close == 3600)
    }


}