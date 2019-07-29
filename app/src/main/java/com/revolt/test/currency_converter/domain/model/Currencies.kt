package com.revolt.test.currency_converter.domain.model

data class Currencies(

    val base: String,

    val date: String,

    val currencyList: List<Currency>
)