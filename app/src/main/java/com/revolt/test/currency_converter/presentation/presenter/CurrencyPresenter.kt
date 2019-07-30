package com.revolt.test.currency_converter.presentation.presenter

import com.revolt.test.currency_converter.domain.usecase.CurrencyCase
import com.revolt.test.currency_converter.presentation.displaymodel.CurrencyDisplay
import com.revolt.test.currency_converter.presentation.ui.CurrencyView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.concurrent.timerTask

class CurrencyPresenter(var useCase: CurrencyCase)  {

    private var coroutineJob: Job = Job()
    private var currencyView: CurrencyView? = null
    private val timer = Timer()

    companion object {
        private const val timeDelay = 60000L
    }

    fun setView(view: CurrencyView) {
        currencyView = view
    }

    fun start() {
        currencyView?.showLoading()
        timer.schedule(timerTask { callApi() }, 0, timeDelay)
    }


    fun callApi() {
        coroutineJob = CoroutineScope(Dispatchers.IO).launch {
                val currencyList = useCase.getCurrencyList()?.let {
                    it.currencyList.map { currency ->
                        CurrencyDisplay(currency.countryCode, currency.rate, calculateLocalCurrency(currency.rate))
                    }
                }
                withContext(Dispatchers.Main) {
                    currencyView?.loadCurrencies(currencyList?.toMutableList())
                    currencyView?.hideLoading()
                }
        }
    }

    private fun calculateLocalCurrency(localCurrency: Double): Double = 1 / localCurrency

    fun cancelTimer() {
        timer.cancel()
    }

    fun onDestroy() {
        cancelTimer()
        coroutineJob.cancel()
    }
}