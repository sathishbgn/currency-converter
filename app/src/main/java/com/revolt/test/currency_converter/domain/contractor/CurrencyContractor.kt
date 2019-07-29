package com.revolt.test.currency_converter.domain.contractor

import com.revolt.test.currency_converter.domain.model.Currencies

interface CurrencyContractor {

    suspend fun getCurrencyList(): Currencies

}