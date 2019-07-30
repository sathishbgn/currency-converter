package com.revolt.test.currency_converter.presentation.displaymodel

data class CurrencyDisplay(
    val countryCode: String,
    var rate: Double,
    val eurAgainstLocalCurrency: Double
)