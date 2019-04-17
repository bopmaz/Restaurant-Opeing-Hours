package com.wolt.restauranttime.presentation.timedisplay

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.databinding.DataBindingUtil
import com.wolt.restauranttime.R
import com.wolt.restauranttime.databinding.HourItemBinding

class TimeListAdapter(private val timeTable: LinkedHashMap<String, String>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val holder: TimeDetailViewHolder

        if (convertView == null) {
            val itemBinding: HourItemBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.hour_item,
                parent,
                false
            )

            holder = TimeDetailViewHolder(itemBinding)
            holder.view = itemBinding.getRoot()
            holder.view.tag = holder
        } else {
            holder = convertView.tag as TimeDetailViewHolder
        }

        holder.bind(timeTable.toList()[position])

        return holder.view
    }

    override fun getItem(position: Int): Any {
        return timeTable.toList()[position]
    }

    override fun getItemId(position: Int): Long {
        return position * 10.toLong()
    }

    override fun getCount(): Int {
        return timeTable.size
    }
}

class TimeDetailViewHolder(private var binding: HourItemBinding) {

    var view = binding.root

    fun bind(pair: Pair<String, String>) {
        binding.textViewDayOfWeek.text = pair.first
        binding.textViewOpeningHours.text = pair.second
    }
}
