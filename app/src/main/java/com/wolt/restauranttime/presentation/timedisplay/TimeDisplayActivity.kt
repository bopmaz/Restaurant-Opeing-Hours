package com.wolt.restauranttime.presentation.timedisplay

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wolt.restauranttime.R
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

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unbound()
    }

//    companion object {
//        @JvmStatic
//        @BindingAdapter("adapter")
//        fun bindList(listView: ListView, viewModel: TimeDisplayViewModel) {
//            val adapter = TimeListAdapter(viewModel.displayRepoList)
//
//            adapter.onItemClickListener = { repoFullName: String, position: Int ->
//                viewModel.onRepoClicked(repoFullName, position)
//            }
//
//            val linearLayoutmanager = LinearLayoutManager(recyclerView.context)
//            recyclerView.layoutManager = linearLayoutmanager
//            recyclerView.addOnScrollListener(object :
//                EndlessRecyclerViewScrollListener(linearLayoutmanager) {
//                override fun onLoadMore(page: Int, totalItemsCount: Int) {
//                    viewModel.fetchMore(page)
//                }
//            })
//            recyclerView.adapter = adapter
//        }
//    }
}