package com.wolt.restauranttime.presentation.timedisplay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wolt.restauranttime.databinding.HourItemBinding

class TimeListAdapter(private val timeTable: LinkedHashMap<String, String>) :
    RecyclerView.Adapter<TimeDetailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeDetailViewHolder {
        return TimeDetailViewHolder(
            HourItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return timeTable.size
    }

    override fun onBindViewHolder(holder: TimeDetailViewHolder, position: Int) {
        holder.bind(timeTable.toList()[position].first, timeTable.toList()[position].second)
    }
}

class TimeDetailViewHolder(private var binding: HourItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    var view = binding.root

    fun bind(dayOfWeek: String, openingHours: String) {
        binding.textViewDayOfWeek.text = dayOfWeek
        binding.textViewOpeningHours.text = openingHours
    }
}
