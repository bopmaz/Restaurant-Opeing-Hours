package com.wolt.restauranttimeparser

object TestCaseStrings {

    const val normalHours = "{\n" +
            " \"monday\" : [],\n" +
            " \"tuesday\" : [\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 64800\n" +
            " }\n" +
            " ],\n" +
            " \"wednesday\" : [],\n" +
            " \"thursday\" : [\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 64800\n" +
            " }\n" +
            " ],\n" +
            " \"friday\" : [\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " }\n" +
            " ],\n" +
            " \"saturday\" : [\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 3600\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " }\n" +
            " ],\n" +
            " \"sunday\" : [\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 3600\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 43200\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 75600\n" +
            " }\n" +
            " ]\n" +
            " }"

    const val twoOpeningHours = "{\n" +
            " \"monday\" : [],\n" +
            " \"tuesday\" : [\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 64800\n" +
            " }\n" +
            " ],\n" +
            " \"wednesday\" : [],\n" +
            " \"thursday\" : [\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 40000\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 50000\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 60000\n" +
            " }\n" +
            " ],\n" +
            " \"friday\" : [\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " }\n" +
            " ],\n" +
            " \"saturday\" : [\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 3600\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " }\n" +
            " ],\n" +
            " \"sunday\" : [\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 3600\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 43200\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 75600\n" +
            " }\n" +
            " ]\n" +
            " }"

    const val twoOpeningHoursOvernight = "{\n" +
            " \"monday\" : [],\n" +
            " \"tuesday\" : [\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 64800\n" +
            " }\n" +
            " ],\n" +
            " \"wednesday\" : [],\n" +
            " \"thursday\" : [\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 40000\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 50000\n" +
            " }\n" +
            " ],\n" +
            " \"friday\" : [\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 60000\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " }\n" +
            " ],\n" +
            " \"saturday\" : [\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 3600\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " }\n" +
            " ],\n" +
            " \"sunday\" : [\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 3600\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 43200\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 75600\n" +
            " }\n" +
            " ]\n" +
            " }"

    const val lastDayOvernight = "{\n" +
            " \"monday\" : [\n " +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 3600\n" +
            " }\n" +
            "],\n" +
            " \"tuesday\" : [\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 64800\n" +
            " }\n" +
            " ],\n" +
            " \"wednesday\" : [],\n" +
            " \"thursday\" : [\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 64800\n" +
            " }\n" +
            " ],\n" +
            " \"friday\" : [\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " }\n" +
            " ],\n" +
            " \"saturday\" : [\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 3600\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 36000\n" +
            " }\n" +
            " ],\n" +
            " \"sunday\" : [\n" +
            " {\n" +
            " \"type\" : \"close\",\n" +
            " \"value\" : 3600\n" +
            " },\n" +
            " {\n" +
            " \"type\" : \"open\",\n" +
            " \"value\" : 43200\n" +
            " }\n" +
            " ]\n" +
            " }"

}