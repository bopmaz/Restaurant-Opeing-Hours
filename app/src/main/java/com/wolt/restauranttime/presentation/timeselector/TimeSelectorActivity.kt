package com.wolt.restauranttime.presentation.timeselector

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.wolt.restauranttime.R
import com.wolt.restauranttime.RestaurantTimeApplication
import com.wolt.restauranttime.databinding.TimeSelectorScreenBinding
import com.wolt.restauranttime.di.ScreenComponent
import com.wolt.restauranttime.di.ScreenModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.time_selector_screen.*
import javax.inject.Inject

class TimeSelectorActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: TimeSelectorViewModel
    private val disposable = CompositeDisposable()

    val screenComponent: ScreenComponent by lazy {
        (application as RestaurantTimeApplication).component.screenComponent(ScreenModule(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        screenComponent.inject(this)

        val binding: TimeSelectorScreenBinding =
            DataBindingUtil.setContentView(this, R.layout.time_selector_screen)

        binding.viewModel = viewModel
        buttonExample1.setOnClickListener { viewModel.fetchExampleApi(1) }
        buttonExample2.setOnClickListener { viewModel.fetchExampleApi(2) }
        buttonExample3.setOnClickListener { viewModel.fetchExampleApi(3) }
        buttonFetch.setOnClickListener { viewModel.fetchLinkApi() }
        buttonGetHours.setOnClickListener { viewModel.getOpeningHours() }

        disposable.add(viewModel.errorObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it.isNotEmpty()) {
                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.clear()
        viewModel.unbound()
    }
}