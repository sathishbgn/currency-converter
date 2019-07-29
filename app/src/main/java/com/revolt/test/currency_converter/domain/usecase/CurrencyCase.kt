package com.revolt.test.currency_converter.domain.usecase

import com.revolt.test.currency_converter.domain.contractor.CurrencyContractor
import com.revolt.test.currency_converter.domain.model.Currencies
import org.koin.core.KoinComponent
import org.koin.core.inject

class CurrencyCase : KoinComponent {

    val contractor: CurrencyContractor by inject()

    suspend fun getCurrencyList(): Currencies? {
        return contractor?.getCurrencyList()
    }

}