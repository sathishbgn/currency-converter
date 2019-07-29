package com.revolt.test.currency_converter.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.DividerItemDecoration
import com.revolt.test.currency_converter.R
import com.revolt.test.currency_converter.presentation.displaymodel.CurrencyDisplay
import com.revolt.test.currency_converter.presentation.presenter.CurrencyPresenter
import kotlinx.android.synthetic.main.activity_main.rvCurrency
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity(), CurrencyView, KoinComponent {

    val presenter: CurrencyPresenter by inject()

    companion object {
        private const val timeDelay = 10000L
    }

    private lateinit var adapter: CurrencyAdapter
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.setView(this@MainActivity)
        presenter.callApi()
        val llm = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvCurrency.layoutManager = llm
        rvCurrency.addItemDecoration( DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

//         handler = Handler().also {
//             it.postDelayed({
//                 presenter.callApi()
//             }, timeDelay)
//         }

//        val t = Timer()
//        t.schedule(myTimerTask, 1000L)
        Executors.newSingleThreadScheduledExecutor().schedule({
            presenter.callApi()
        }, 0, TimeUnit.MINUTES)
    }


    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun loadCurrencies(display: MutableList<CurrencyDisplay>?) {
        display?.let {
            adapter = CurrencyAdapter(display, this@MainActivity)
            rvCurrency.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    override fun onBackPressed() {
        onDestroy()
        super.onBackPressed()
    }

    override fun onDestroy() {
//        handler.removeCallbacks(null)
        presenter.onDestroy()
        super.onDestroy()
    }
}
