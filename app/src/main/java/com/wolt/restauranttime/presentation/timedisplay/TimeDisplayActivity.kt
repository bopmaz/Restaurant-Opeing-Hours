package com.wolt.restauranttime.presentation.timedisplay

import android.os.Bundle
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wolt.restauranttime.BaseActivity
import com.wolt.restauranttime.R
import com.wolt.restauranttime.databinding.TimeDisplayScreenBinding
import javax.inject.Inject

class TimeDisplayActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: TimeDisplayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenComponent.inject(this)

        val binding: TimeDisplayScreenBinding =
            DataBindingUtil.setContentView(this, R.layout.time_display_screen)
        binding.viewModel = viewModel

    }

    companion object {
        @JvmStatic
        @BindingAdapter("adapter")
        fun bindList(recyclerView: RecyclerView, viewModel: TimeDisplayViewModel) {
            val adapter = TimeListAdapter(viewModel.getDisplayOpeningHours())
            val linearLayoutmanager = LinearLayoutManager(recyclerView.context)
            recyclerView.layoutManager = linearLayoutmanager
            recyclerView.adapter = adapter
        }
    }
}