package com.revolt.test.currency_converter.presentation.ui

import com.revolt.test.currency_converter.presentation.displaymodel.CurrencyDisplay

interface CurrencyView {

    fun showLoading()
    fun hideLoading()
    fun loadCurrencies(display: MutableList<CurrencyDisplay>?)

}