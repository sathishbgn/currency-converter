package com.revolt.test.currency_converter.data.network

import com.revolt.test.currency_converter.data.entity.CurrencyEntity
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface CurrencyService {

    @GET("/latest?base=EUR")
    fun getCurrencyList() : Deferred<Response<CurrencyEntity>>
}