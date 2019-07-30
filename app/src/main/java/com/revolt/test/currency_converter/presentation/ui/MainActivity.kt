package com.revolt.test.currency_converter.presentation.ui

import android.app.AlertDialog
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import com.revolt.test.currency_converter.R
import com.revolt.test.currency_converter.presentation.displaymodel.CurrencyDisplay
import com.revolt.test.currency_converter.presentation.presenter.CurrencyPresenter
import kotlinx.android.synthetic.main.activity_main.rvCurrency
import org.koin.android.ext.android.inject
import org.koin.core.KoinComponent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), CurrencyView, KoinComponent, CurrencyAdapter.UpdateCurrencyList {

    val presenter: CurrencyPresenter by inject()

    private lateinit var adapter: CurrencyAdapter
    private var progressDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.setView(this@MainActivity)
        presenter.start()
        val llm = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvCurrency.layoutManager = llm
        rvCurrency.addItemDecoration( DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
    }


    override fun showLoading() {
        progressDialog = ProgressDialog.show(this@MainActivity, "",
            "Loading. Please wait...", true)
    }

    override fun hideLoading() {
        if (!isFinishing) {
            progressDialog?.let {
                if (it.isShowing) {
                    it.dismiss()
                }
            }
        }
    }

    override fun loadCurrencies(display: MutableList<CurrencyDisplay>?) {
        display?.let {
            adapter = CurrencyAdapter(display, this@MainActivity)
            adapter.setListener(this@MainActivity)
            rvCurrency.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    override fun onValueChange() {
        rvCurrency.post(Runnable {
            presenter.cancelTimer()
            adapter.removeWatcher()
            adapter.notifyDataSetChanged()
        })
    }

    override fun onBackPressed() {
        super.onBackPressed()
        presenter.onDestroy()
        finish()
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
