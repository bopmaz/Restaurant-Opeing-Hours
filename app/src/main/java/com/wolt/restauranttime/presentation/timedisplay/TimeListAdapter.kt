package com.wolt.restauranttime.presentation.timedisplay

import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.wolt.restauranttimeparser.Timetable

class TimeListAdapter(private val timeTable: Timetable) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

    }

    override fun getItem(position: Int): Any {
    }

    override fun getItemId(position: Int): Long {
    }

    override fun getCount(): Int {
        timeTable.time.size
    }

}