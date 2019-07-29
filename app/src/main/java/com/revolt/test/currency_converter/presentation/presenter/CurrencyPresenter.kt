package com.revolt.test.currency_converter.presentation.presenter

import com.revolt.test.currency_converter.domain.usecase.CurrencyCase
import com.revolt.test.currency_converter.presentation.displaymodel.CurrencyDisplay
import com.revolt.test.currency_converter.presentation.ui.CurrencyView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyPresenter(var useCase: CurrencyCase)  {

    private var coroutineJob: Job = Job()
    private var currencyView: CurrencyView? = null

//    private var job: Job = Job()
//    private val scope = CoroutineScope(coroutineJob + Dispatchers.Default)

    fun setView(view: CurrencyView) {
        currencyView = view
    }

    fun callApi() {
//        scope.launch {
//            val currencyList = useCase.getCurrencyList()?.let {
//                it.currencyList.map { currency ->
//                    CurrencyDisplay(currency.countryCode, currency.rate)
//                }
//            }
//            currencyView?.loadCurrencies(currencyList)
//            currencyView?.hideLoading()
//        }
        coroutineJob = CoroutineScope(Dispatchers.IO).launch {
                currencyView?.showLoading()
                val currencyList = useCase.getCurrencyList()?.let {
                    it.currencyList.map { currency ->
                        CurrencyDisplay(currency.countryCode, currency.rate)
                    }
                }
                withContext(Dispatchers.Main) {
                    currencyView?.loadCurrencies(currencyList?.toMutableList())
                    currencyView?.hideLoading()
                }
        }
    }

    fun onDestroy() {
        coroutineJob.cancel()
    }

}