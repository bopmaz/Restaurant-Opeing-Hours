package com.wolt.restauranttime.presentation.timedisplay

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.wolt.restauranttime.R
import com.wolt.restauranttime.databinding.TimeDisplayScreenBinding
import javax.inject.Inject

class TimeDisplayActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: TimeDisplayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: TimeDisplayScreenBinding =
            DataBindingUtil.setContentView(this, R.layout.time_display_screen)
        binding.viewModel = viewModel

    }

    companion object {
        @JvmStatic
        @BindingAdapter("listAdapter")
        fun bindList(listView: ListView, viewModel: TimeDisplayViewModel) {
            val adapter = TimeListAdapter(viewModel.getDisplayOpeningHours())
            listView.adapter = adapter
        }
    }
}