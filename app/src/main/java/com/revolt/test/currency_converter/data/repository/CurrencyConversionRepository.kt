package com.revolt.test.currency_converter.data.repository

import com.revolt.test.currency_converter.data.entity.CurrencyEntity
import com.revolt.test.currency_converter.data.network.CurrencyService

class CurrencyConversionRepository(private val apiService: CurrencyService) : BaseRepository() {

    suspend fun getCurrecyList(): CurrencyEntity? {
        val response = execute(
            call = { apiService.getCurrencyList().await()},
            errorMessage = "Oops!! Something went wrong"
        )
        return response;
    }
}