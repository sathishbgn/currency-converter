package com.revolt.test.currency_converter.data.repository

import com.revolt.test.currency_converter.data.CurrencyMapper
import com.revolt.test.currency_converter.data.network.CurrencyApi
import com.revolt.test.currency_converter.domain.model.Currencies
import com.revolt.test.currency_converter.domain.contractor.CurrencyContractor
import org.koin.core.KoinComponent

class CurrencyRepository(private val api: CurrencyApi) : BaseRepository(), CurrencyContractor, KoinComponent {

    override suspend fun getCurrencyList(): Currencies {
        val response = execute(
            call = { api.getCurrencyList().await()},
            errorMessage = "Oops!! Something went wrong"
        )
        return response.let {
            CurrencyMapper().transformCurrency(it)
        }
    }
}